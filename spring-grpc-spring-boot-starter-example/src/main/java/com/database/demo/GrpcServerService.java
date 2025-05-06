package com.database.demo;

import com.database.protobuf.HelloReply;
import com.database.protobuf.HelloRequest;
import com.database.protobuf.SimpleGrpc;
import io.grpc.stub.StreamObserver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
class GrpcServerService extends SimpleGrpc.SimpleImplBase {
    final UsersService usersService;
    AtomicInteger userIdGenerator = new AtomicInteger(0);
    List<String> groups = List.of("group1", "group2", "group3");

    private static Log log = LogFactory.getLog(GrpcServerService.class);

    public GrpcServerService(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        log.info("Hello " + req.getName());
        if (req.getName().startsWith("error")) {
            throw new IllegalArgumentException("Bad name: " + req.getName());
        }
        if (req.getName().startsWith("internal")) {
            throw new RuntimeException();
        }
        User user = new User(req.getName(), "test", "test");
        user.setCreatedAt(java.time.LocalDateTime.now());
        user.setId(userIdGenerator.getAndIncrement());
        user.getGroups().add(groups.get(user.getId() % groups.size()));
        user.getGroups().add(groups.get((user.getId() + 1) % groups.size()));
        usersService.saveUser(user);

        HelloReply reply = HelloReply.newBuilder().setMessage("Hello ==> " + req.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void streamHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        log.info("Hello " + req.getName());
        int count = 0;
        while (count < 10) {
            HelloReply reply = HelloReply.newBuilder().setMessage("Hello(" + count + ") ==> " + req.getName()).build();
            responseObserver.onNext(reply);
            count++;
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                responseObserver.onError(e);
                return;
            }
        }
        responseObserver.onCompleted();
    }
}