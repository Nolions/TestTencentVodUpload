import com.qcloud.vod.model.VodUploadRequest
import com.qcloud.vod.model.VodUploadResponse
import com.tencentcloudapi.vod.v20180717.models.DeleteMediaRequest
import com.tencentcloudapi.vod.v20180717.models.DeleteMediaResponse
import tw.nolions.testTencentVod.BuildConfig

class VideoUpdate {
    fun createVodRequest(model: VodRequest): VodUploadRequest {
        val req = VodUploadRequest()
        req.mediaFilePath = model.media
        req.coverFilePath = model.cover
        req.mediaName = model.name
        req.procedure = Tencent.VodProcedure
        req.subAppId = Tencent.VodSubAppId

        return req
    }

    suspend fun updateVideo(req: VodUploadRequest): VodUploadResponse {
        return Tencent.vodUploadClient.upload(BuildConfig.VOD_REGION, req)
    }

    suspend fun deleteVideo(fileId: String): DeleteMediaResponse? {
        val delReq = DeleteMediaRequest()
        delReq.fileId = fileId
        delReq.subAppId = BuildConfig.VOD_SUB_APP_ID

        return Tencent.vodClient.DeleteMedia(delReq)
    }
}

data class VodRequest(val media: String, val cover: String, val name: String)