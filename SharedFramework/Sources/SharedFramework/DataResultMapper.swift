import Shared
import IosModel

enum KmpError: Error {
    case error(
        message: String,
        stacktrace: String,
    )
}

protocol DataResultMapper {
    
    func map<KT, ST>(
        kmpResult: Shared.DataResult<KT>,
        mapData: (KT) -> ST
    ) -> IosModel.DataResult<ST>
}

class LiveDataResultMapper : DataResultMapper {
    func map<KT, ST>(
        kmpResult: Shared.DataResult<KT>,
        mapData: (KT) -> ST
    ) -> IosModel.DataResult<ST> {
        let mappedResult: IosModel.DataResult<ST>
        
        switch onEnum(of: kmpResult) {
        case .error(let result):
            let error = KmpError.error(
                message: result.exception.message ?? "",
                stacktrace: result.exception.getStacktraceString()
            )
            mappedResult = IosModel.DataResult<ST>.error(error)
            break
        case .success(let result):
            let data = mapData(result.data!)
            mappedResult = IosModel.DataResult<ST>.success(data)
            break
        }
        
        return mappedResult
    }
}
