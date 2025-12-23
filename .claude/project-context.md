# TicTacToe KMP - Claude Code Project Context

This file provides quick context for Claude Code to understand the project's current state, key decisions, and common tasks.

---

## Current Focus

Building a **Kotlin Multiplatform Mobile** TicTacToe game with:
- **iOS app** using SwiftUI (primary development focus)
- **Android app** placeholder (future implementation with Jetpack Compose)
- **Shared business logic** in Kotlin Multiplatform

---

## Key Architectural Decisions

### Why SKIE (Swift Kotlin Interface Enhancer)?

**Problem**: Standard Kotlin/Native Swift interop produces verbose, non-idiomatic Swift APIs.

**Solution**: SKIE 0.10.6 provides:
- Kotlin `suspend` functions → Swift `async/await`
- Kotlin `Flow<T>` → Swift `AsyncSequence<T>` / Combine `Publisher`
- Sealed classes → Swift `enum` (not clunky structs)
- Data classes → Swift `struct` with proper Equatable/Hashable

**Impact**: iOS developers can use Kotlin code as if it were native Swift.

---

### Why Swift Package Manager (not CocoaPods)?

**Reasons**:
- Modern iOS dependency management (Xcode native)
- Better integration with Xcode build system
- Supports binary targets for XCFramework distribution
- Simpler dependency graph without Ruby/CocoaPods overhead
- Future-proof (Apple's direction)

**Trade-off**: Requires Xcode 12+ and manual XCFramework path management.

---

### Module Structure Philosophy

**Three-tier architecture**:

1. **Core Modules** (`core/*`) - Platform abstractions
   - No dependencies on other multiplatform modules
   - Provide utilities: logging, data storage, error handling
   - Reusable across all library modules

2. **Library Modules** (`library/*`) - Business logic
   - Depend only on core modules (auto-injected by convention plugin)
   - Pure domain logic: use cases, repositories, models
   - Platform-agnostic

3. **Shared Module** (`shared`) - Export aggregation
   - Exports everything as iOS XCFramework via SKIE
   - No business logic itself
   - Acts as a facade for iOS consumption

**Rationale**: Clear separation of concerns, minimal coupling, easy to test.

---

### Convention Plugins for Build Consistency

**Problem**: 6 multiplatform modules with duplicate configuration.

**Solution**: Custom Gradle convention plugins in `build-logic/`.

**Benefits**:
- **MultiplatformLibraryPlugin** auto-configures:
  - Android + iOS targets
  - Koin, Coroutines, DateTime dependencies
  - Auto-injects `core/common` and `core/logger` (unless module is core itself)
  - Detekt linting
- Modules use simple: `alias(libs.plugins.local.multiplatform.library)`
- Changes propagate to all modules instantly
- Enforces consistency

**Example**: Adding a new dependency to all modules = one-line change in convention plugin.

---

## Current Project State

### ✅ Implemented

- [x] Kotlin Multiplatform module structure (7 modules)
- [x] iOS app with game creation flow (SwiftUI + Factory DI)
- [x] SKIE integration (0.10.6)
- [x] Swift-Kotlin DI bridge (Koin → DiWrapper → Factory)
- [x] DataResult error handling pattern with monad operators
- [x] XCFramework build pipeline
- [x] Convention plugins (4 custom plugins)
- [x] Logging framework (platform-specific writers)
- [x] DataStore persistence layer

### 🚧 In Progress

- [ ] Game board UI implementation
- [ ] Game logic (move validation, win detection)
- [ ] Multiplayer networking layer (Ktor client configured, not used yet)

### 📋 Planned / Future

- [ ] **Android app implementation**
  - Currently the `app` module is empty (placeholder)
  - Will use Jetpack Compose for UI
  - Simpler than iOS (no DI bridge needed, Koin direct)

- [ ] **Backend integration**
  - Ktor client already configured
  - Need backend API for multiplayer
  - Real-time updates via WebSockets or Server-Sent Events

- [ ] **Testing**
  - Unit tests in `commonTest` (shared logic)
  - Platform-specific tests (Android/iOS)
  - UI tests (Compose/SwiftUI)

- [ ] **CI/CD Pipeline**
  - GitHub Actions for automated builds
  - Run tests, Detekt, build XCFramework
  - Publish releases

---

## Common Tasks

### Rebuild iOS Framework After Kotlin Changes

**Why**: iOS app links against pre-built XCFramework, doesn't auto-detect Kotlin changes.

```bash
# Clean old build
./gradlew :multiplatform:shared:clean

# Rebuild XCFramework (Debug)
./gradlew :multiplatform:shared:assembleSharedDebugXCFramework

# Or use shorthand task
./gradlew :multiplatform:shared:buildAndCopyDebugXCFrameworkToCommonFolder
```

**Output**: `multiplatform/shared/build/XCFrameworks/lib/Shared.xcframework`

---

### Add New Shared Module

1. **Create directory**: `multiplatform/{core|library}/module-name`
2. **Add** `build.gradle.kts` with convention plugin
3. **Add to** `settings.gradle.kts`: `include(":multiplatform:library:module-name")`
4. **Rebuild**: Convention plugin auto-configures, `shared` module auto-exports
5. **Rebuild XCFramework** for iOS to see changes

---

### Debug Kotlin Code from Xcode

SKIE generates dSYM files → you can debug Kotlin directly in Xcode!

1. Build Debug XCFramework (includes debug symbols)
2. Set breakpoints in Xcode (even on Kotlin call sites)
3. Use `po` / `print` commands to inspect Kotlin objects
4. Step through Kotlin code execution

**Tip**: Check dSYM exists: `ls multiplatform/shared/build/XCFrameworks/debug/Shared.xcframework.dSYM`

---

## Known Issues & Quirks

### XCFramework Build is Slow (~1-2 minutes)

**Cause**: SKIE code generation overhead + multi-architecture compilation (arm64 device + simulator).

**Workarounds**:
- Use `buildAndCopy*ToCommonFolder` tasks (copies output for reuse)
- Only rebuild when Kotlin code changes (not on every Xcode build)
- Consider CI for release builds

---

### Android App is Empty

**Status**: The `/app` module exists but has no source files.

**Reason**: iOS-first development approach.

**Plan**: Will implement Android UI with Jetpack Compose once iOS is stable.

---

### No Backend / Networking Yet

**Status**: Ktor 3.2.3 is configured in `library/networking` but not actively used.

**Reason**: Building local game logic first, multiplayer later.

**Plan**: RESTful API or GraphQL backend for game state sync.

---

### Package Name Typo

**Issue**: Some packages use `mutliplatform` instead of `multiplatform`.

**Decision**: Keep for consistency with existing code, don't fix (would require large refactor).

**Location**: `cz.johnyapps.mutliplatform.library.game.*` (note missing 'i')

---

## Dependencies of Note

### Koin 4.0.2 (Kotlin DI)

- Used in shared Kotlin code
- Modules: `coreModule`, `gameModule`, etc.
- Exposed to Swift via `DiResolver.shared`

**Why not Dagger/Hilt**: Simpler, multiplatform-friendly, no annotation processing.

---

### Factory 2.5.3 (Swift DI)

- Used on iOS side
- Bridges to Koin via `DiWrapper.getting()`
- Property wrapper: `@Injected(\.createGameUC)`

**Why not pure Koin on iOS**: Factory provides idiomatic Swift DI experience.

---

### Ktor 3.2.3 (Networking)

- HTTP client for future backend integration
- Platform engines: OkHttp (Android), Darwin (iOS)
- Features: logging, content negotiation, auth

**Status**: Configured but not actively used yet.

---

### DataStore 1.1.4 (Persistence)

- Key-value storage (androidx.datastore)
- **Skipped 1.1.5**: Known bug in that version
- Works on both Android and iOS

**Use case**: Save game state, user preferences.

---

### SKIE 0.10.6 (Swift/Kotlin Interop)

- Critical for iOS integration
- Auto-generates Swift-friendly APIs from Kotlin

**Config**: `multiplatform/shared/build.gradle.kts:56-73`

---

## Critical File Locations

### Build Configuration

- **Version Catalog**: `gradle/libs.versions.toml` - All dependency versions
- **Settings**: `settings.gradle.kts` - Module includes
- **SKIE Config**: `multiplatform/shared/build.gradle.kts:56-73` - Interop features
- **Convention Plugins**: `build-logic/convention/src/main/kotlin/cz/johnyapps/tictactoe/convention/plugin/`

### Core Patterns

- **DataResult**: `multiplatform/core/common/src/commonMain/kotlin/.../util/model/DataResult.kt`
  - Monad for error handling
  - Used by all use cases

- **Use Case Example**: `multiplatform/library/game/src/commonMain/kotlin/.../domain/CreateGameUseCase.kt`
  - Shows `operator fun invoke()` pattern
  - Returns `DataResult<T>`

### iOS Integration

- **Package Manifest**: `ios/SharedFramework/Package.swift`
  - Links to XCFramework: `../../multiplatform/shared/build/XCFrameworks/lib/Shared.xcframework`
  - Declares dependencies: Factory, IosModel

- **DI Bridge**: `ios/SharedFramework/Sources/SharedFramework/DiWrapper.swift`
  - Bridges Koin (Kotlin) to Factory (Swift)
  - Critical for dependency injection flow

---

## Patterns to Follow

### DataResult Pattern (Error Handling)

**Kotlin**:
```kotlin
suspend fun invoke(): DataResult<GameId>
```

**Swift (via SKIE)**:
```swift
let result = await useCase.invoke()
switch result {
case .success(let gameId):
    // Handle success
case .error(let exception):
    // Handle error
}
```

**Why**: Type-safe error handling without exceptions, works across platforms.

---

### Use Case Pattern

**Interface**:
```kotlin
interface CreateGameUseCase {
    suspend operator fun invoke(): DataResult<GameId>
}
```

**Implementation**:
```kotlin
internal class LiveCreateGameUseCase(
    private val gameSource: GameSource,
    private val caller: GameCaller,
) : CreateGameUseCase {
    override suspend fun invoke(): DataResult<GameId> {
        return caller.callGame {
            gameSource.createGame()
        }
    }
}
```

**Usage**:
```kotlin
val gameId = createGameUseCase() // Clean call syntax via operator
```

---

### Dependency Injection Flow (iOS)

```
SwiftUI View
    ↓ @Injected(\.createGameUC)
Factory (Swift)
    ↓ DiWrapper.getting(CreateGameUseCase.self)
Koin (Kotlin)
    ↓ Resolves LiveCreateGameUseCase
Use Case executes
    ↓ Returns DataResult<GameId>
SKIE converts sealed class → enum
    ↓
Swift receives enum DataResult<GameId>
```

---

## Quick Reference Commands

```bash
# Clean build
./gradlew clean

# Build all modules
./gradlew build

# Run tests
./gradlew test

# Run linter - if fails, run for second time. Some issues might be resolved automatically
./gradlew detekt --continue

# Build iOS XCFramework (Debug)
./gradlew :multiplatform:shared:buildAndCopyDebugXCFrameworkToCommonFolder

# Build Android APK
./gradlew :app:assembleDebug

# Open iOS in Xcode
open ios/tictactoe/tictactoe.xcodeproj
```

---

## When You Need to...

### Change Kotlin code → iOS sees it

1. Edit Kotlin files
2. `./gradlew :multiplatform:shared:buildAndCopyDebugXCFrameworkToCommonFolder`
3. Rebuild in Xcode

### Add new dependency to all modules

1. Add to `gradle/libs.versions.toml`
2. Add to convention plugin (`MultiplatformLibraryPlugin.kt`)
3. Sync Gradle

### Add new use case

1. Create interface in `domain/` package
2. Create implementation (internal class)
3. Register in Koin module
4. Rebuild XCFramework for iOS
5. Register in Factory (iOS side)

### Debug why iOS can't see Kotlin class

1. Check class is `public` (not `internal`)
2. Verify exported from `shared` module
3. Check SKIE features enabled for that type
4. Rebuild XCFramework
5. Check SKIE build logs for warnings

---

## Additional Context

- **Primary developer focus**: iOS implementation first, Android second
- **Code quality**: Using Detekt, official Kotlin style
- **Namespace**: `cz.johnyapps.tictactoe` (Czech developer/company)

---

**Last Updated**: 2025-12-23
