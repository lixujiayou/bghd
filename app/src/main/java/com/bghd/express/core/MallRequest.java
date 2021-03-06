package com.bghd.express.core;





import com.bghd.express.entiy.AdressEntity;
import com.bghd.express.entiy.OrderListEntity;
import com.bghd.express.entiy.TellEntity;
import com.bghd.express.entiy.UserEntity;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


/**
 * Created by Administrator on 2016/11/29.
 */
public interface MallRequest {

    @FormUrlEncoded
    @POST(AllUrl.mLoginUrl)
    Observable<UserEntity> login(@Field("mobile") String userName, @Field("password") String pwd);

    @FormUrlEncoded
    @POST(AllUrl.mGetOrderList)
    Observable<OrderListEntity> getOrderList(@Field("uid") String userName
            ,@Field("type") String type
            ,@Field("page") int page
            ,@Field("size") int size);


    @FormUrlEncoded
    @POST(AllUrl.mGetAdressList)
    Observable<AdressEntity> getAdressList(@Field("id") String id);


    @FormUrlEncoded
    @POST(AllUrl.mGetTellList)
    Observable<TellEntity> getTellList(@Field("page") String page
            ,@Field("size") String size
            ,@Field("uid") String uid
            ,@Field("type") String type
            ,@Field("truename") String truename);



//
//    @FormUrlEncoded
//    @POST(AllUrl.mGetRoundWaiNumUrl)
//    Observable<WaitSiteEntity> getRoundWaitList(@Field("userAccount") String userName, @Field("myLatitude") String lat, @Field("myLongitude") String lon);
//
//
//    @FormUrlEncoded
//    @POST(AllUrl.mGetMetersOfSiteUrl)
//    Observable<WaitMeterEntity> getMeterBySite1(@Field("siteName") String userName);
//
//    @FormUrlEncoded
//    @POST(AllUrl.mGetMetersOfSiteUrl2)
//    Observable<MeterBySite2Entity> getMeterBySite2(@Field("siteName") String userName);
//
//
//    @FormUrlEncoded
//    @POST(AllUrl.mGetRoundSiteUrl)
//    Observable<RoundSiteEntity> getRoundSite(@Field("userAccount") String userName, @Field("siteName") String siteName, @Field("myLatitude") String lat, @Field("myLongitude") String lon);
//
//
//    @FormUrlEncoded
//    @POST(AllUrl.mMyRankUrl)
//    Observable<MyRankEntity> getMyRank(@Field("userName") String userName);
//
//    @FormUrlEncoded
//    @POST(AllUrl.mRankingUrl)
//    Observable<RankingEntity> getRanking(@Field("userName") String userName);
//
//    @FormUrlEncoded
//    @POST(AllUrl.mWorkingUrl)
//    Observable<MyWorkEntity> getMyWorking(@Field("userName") String userName);
//
//    @FormUrlEncoded
//    @POST(AllUrl.mChangePwdUrl)
//    Observable<ChangePwdEntity> changePwd(@Field("account") String userName, @Field("newPassword") String newPassword, @Field("oldPassword") String oldPassword);
//
//
//    @FormUrlEncoded
//    @POST(AllUrl.mFeedbackUrl)
//    Observable<IdeaEntity> feedback(@Field("userAccount") String userName, @Field("myTalk") String content, @Field("phoneOrEmail") String tell, @Field("criticLevel") String level);
//
//    //通过电表id获取电表详情
//    @FormUrlEncoded
//    @POST(AllUrl.mGetMeterInfoByIdUrl)
//    Observable<MeterInfoEntity> getMeterInfoById(@Field("meterCode") String meterCode);
//
//    //电表保存
//    @FormUrlEncoded
//    @POST(AllUrl.mSaveMeterUrl)
//    Observable<SaveResultEntity> saveMeter(
//            @Field("meterCode") String meterCode
//            , @Field("meterMagnifi") String meterMagnifi
//            , @Field("thisReadingNum") String thisReadingNum
//            , @Field("ThisReadingDateStr") String ThisReadingDateStr
//            , @Field("lastReadingNum") String lastReadingNum
//            , @Field("LastReadingDateStr") String LastReadingDateStr
//            , @Field("ammeterReMetering") String ammeterReMetering
//            , @Field("thisWeekReading") String thisWeekReading
//            , @Field("thisWeekDailyElectricity") String thisWeekDailyElectricity
//            , @Field("lastWeekDailyElectricity") String lastWeekDailyElectricity
//            , @Field("meterReader") String meterReader
//            , @Field("meterReaderPhone") String meterReaderPhone
//            , @Field("createUser") String createUser
//            , @Field("imgBase64") String imgBase64
//            , @Field("warningNo") String warningNo
//            , @Field("siteName") String siteName
//    );
//
//
//
//
//
//    ////获取周围电表======
//    @FormUrlEncoded
//    @POST(AllUrl.mGetRoundMeterUrl)
//    Observable<MeterListBean> getRoundMeter(
//            @Field("pageIndex") String pageIndex
//            , @Field("pageSize") String pageSize
//            , @Field("longitude") String longitude
//            , @Field("latitude") String latitude
//            , @Field("distance") String distance
//            , @Field("userAccount") String userAccount
//    );
//
//
//    //获解析图片的账号
//    @FormUrlEncoded
//    @POST(AllUrl.mGetAccount)
//    Observable<ParseAccountEntity> getAccount(@Field("meterCode") String meterCode);
//
//    //获解析图片的账号token
//    @FormUrlEncoded
//    @POST(AllUrl.mGetAccountToken)
//    Observable<AccountTokenEntity> getAccountToken(
//            @Field("grant_type") String meterCode
//            , @Field("client_id") String client_id
//            , @Field("client_secret") String client_secret
//    );
//
//    //识别图片
//    @FormUrlEncoded
//    @POST(AllUrl.mParseImgUrl)
//    Observable<ImgParseResultEntity> parseImg(
//            @Field("access_token") String meterCode
//            , @Field("image") String client_id
//    );


    /*@FormUrlEncoded
    @POST(AllUrl.queryList)
    Call<String> getBookList(@Field("UID") String uid
            , @Field("start") String page
            , @Field("pageSize") String pageSize
            , @Field("cityId") String cityId
            , @Field("countyId") String countyId);*/
//    @FormUrlEncoded
//    @POST(AllUrl.queryList)
//    Call<String> getBookList(@Field("UID") String uid);


    /**
     * 上传一张图片
     * @return
     */
    /*@Multipart
    @POST(AllUrl.uploadImageTrue)
    Call<StringBean> uploadImageTrue(@PartMap Map<String, RequestBody> params);



    @FormUrlEncoded
    @POST(AllUrl.uploadImage)
    Call<UpLoadResult> uploadImage(
            @Field("id") String id
            , @Field("photoString") String photoString);*/



    /*@Multipart
    @POST()
    Call<ResponseBody> uploadFiles(@Url String url, @PartMap() Map<String, RequestBody> maps);


    @Multipart
    @POST
    Call<ResponseBody> uploadFileWithPartMap(
            @Url() String url,
            @PartMap() Map<String, RequestBody> partMap,
            @Part("file") MultipartBody.Part file);



    @Multipart
    @POST(AllUrl.uploadImage)
    Call<String> updateImage(@Part MultipartBody.Part[] file, @QueryMap Map<String, String> maps);


    @Streaming //大文件时要加不然会OOM
    @FormUrlEncoded
    @POST
    Call<ResponseBody> downloadFile(@Url String fileUrl, @Field("jsonRequest") String jsonRequest);*/
}
