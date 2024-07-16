package org.tianzun.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.tianzun.api.LoginServiceGrpc;
import org.tianzun.api.UserProto;

@GrpcService
public class LoginService extends LoginServiceGrpc.LoginServiceImplBase {

    @Override
    public void login(UserProto.LoginRequest request, StreamObserver<UserProto.LoginResponse> responseObserver) {
        // 请求参数处理
        String username = request.getUsername();
        String password = request.getPassword();
        // 构建返回值
        UserProto.LoginResponse resp = UserProto.LoginResponse.newBuilder()
                .setSuccess(true)
                .setSystem("Java")
                .setToken(username + ":" + password)
                .build();
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
    }
}
