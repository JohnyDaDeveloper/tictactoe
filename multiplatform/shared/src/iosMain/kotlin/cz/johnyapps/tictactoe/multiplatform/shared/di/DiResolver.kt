package cz.johnyapps.tictactoe.multiplatform.shared.di

import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ObjCClass
import kotlinx.cinterop.ObjCObject
import kotlinx.cinterop.ObjCProtocol
import kotlinx.cinterop.getOriginalKotlinClass
import org.koin.core.component.KoinComponent
import kotlin.reflect.KClass

@OptIn(BetaInteropApi::class)
@Suppress("unused")
object DiResolver : KoinComponent {

    fun <T : Any> resolve(
        clazz: ObjCObject,
    ): T {
        val kotlinClass = toKotlinClass(clazz)
        return getKoin().get(kotlinClass)
    }

    private fun toKotlinClass(clazz: ObjCObject): KClass<*> {
        return when (clazz) {
            is ObjCProtocol -> {
                getOriginalKotlinClass(clazz)
            }

            is ObjCClass -> {
                getOriginalKotlinClass(clazz)
            }

            else -> {
                throw IllegalArgumentException("Unknown class type: $clazz")
            }
        } ?: throw IllegalArgumentException("Failed to get type info of class $clazz")
    }
}
