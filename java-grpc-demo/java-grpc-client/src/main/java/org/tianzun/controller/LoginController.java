package org.tianzun.controller;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.web.bind.annotation.*;
import org.tianzun.api.LoginRequestDTO;
import org.tianzun.api.LoginResponseDTO;
import org.tianzun.api.LoginServiceGrpc;
import org.tianzun.api.UserProto;

@RestController
@RequestMapping("user")
public class LoginController {

    @GrpcClient("java-grpc-server")
    private LoginServiceGrpc.LoginServiceBlockingStub loginServiceBlockingStub;

    @PostMapping("login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO req) {
        // 组装rpc请求参数
        UserProto.LoginRequest loginRequest = UserProto.LoginRequest.newBuilder()
                .setUsername(req.getUsername())
                .setPassword(req.getPassword())
                .build();
        // 发送rpc请求
        UserProto.LoginResponse stubResp = loginServiceBlockingStub.login(loginRequest);
        // 构建返回值
        LoginResponseDTO loginResp = new LoginResponseDTO();
        loginResp.setSuccess(stubResp.getSuccess());
        loginResp.setSystem(stubResp.getSystem());
        loginResp.setToken(stubResp.getToken());
        return loginResp;
    }
}
