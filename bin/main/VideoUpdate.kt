import com.qcloud.vod.VodUploadClient
import com.qcloud.vod.model.VodUploadRequest
import com.qcloud.vod.model.VodUploadResponse
import tw.nolions.testTencentVod.BuildConfig

class VideoUpdate {
    private var client: VodUploadClient = VodUploadClient(BuildConfig.VOD_SECRET_ID, BuildConfig.VOD_SECRET_KEY)

    var procedure = BuildConfig.VOD_PROCEDURE

    var subAppId = BuildConfig.VOD_SUB_APP_ID

    fun createVodRequest(model: VodRequest): VodUploadRequest {
        val req = VodUploadRequest()
        req.mediaFilePath = model.media
        req.coverFilePath = model.cover
        req.mediaName = model.name
        req.procedure = procedure
        req.subAppId = subAppId

        return req
    }

    suspend fun updateVideo(req: VodUploadRequest): VodUploadResponse {
        return client.upload(BuildConfig.VOD_REGION, req)
    }
}

data class VodRequest(val media: String, val cover: String, val name: String)