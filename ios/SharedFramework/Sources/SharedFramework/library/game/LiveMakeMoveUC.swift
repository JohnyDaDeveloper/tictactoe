import IosModel
@preconcurrency import Shared

struct LiveMakeMoveUC : MakeMoveUC {
    
    private let makeMoveUseCase: MakeMoveUseCase
    private let makeMoveMapper: MakeMoveMapper
    private let dataResultMapper: DataResultMapper
    
    init(
        makeMoveUseCase: MakeMoveUseCase,
        makeMoveMapper: MakeMoveMapper,
        dataResultMapper: DataResultMapper,
    ) {
        self.makeMoveUseCase = makeMoveUseCase
        self.makeMoveMapper = makeMoveMapper
        self.dataResultMapper = dataResultMapper
    }
    
    func invoke(makeMove: IosModel.MakeMove) async -> IosModel.DataResult<Void> {
        let sharedMakeMove = makeMoveMapper.map(makeMove)
        let result = try! await makeMoveUseCase.invoke(makeMove: sharedMakeMove)
        return dataResultMapper.mapResult(kmpResult: result) { unit in }
    }
}
