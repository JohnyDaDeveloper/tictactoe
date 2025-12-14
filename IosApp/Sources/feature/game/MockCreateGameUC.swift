import IosModel

struct MockCreateGameUC : CreateGameUC {
    func invoke() async throws -> IosModel.DataResult<IosModel.GameId> {
        return .success(.init(value: "Mock ID"))
    }
}
