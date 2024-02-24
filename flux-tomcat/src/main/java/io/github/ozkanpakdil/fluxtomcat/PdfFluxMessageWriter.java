package io.github.ozkanpakdil.fluxtomcat;

import org.reactivestreams.Publisher;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Component
public class PdfFluxMessageWriter implements HttpMessageWriter<Flux<DataBuffer>> {

    @Override
    public List<MediaType> getWritableMediaTypes() {
        return List.of(MediaType.APPLICATION_PDF);
    }

    @Override
    public boolean canWrite(ResolvableType resolvableType, MediaType mediaType) {
        return resolvableType.getRawClass() == Flux.class && mediaType.isCompatibleWith(MediaType.APPLICATION_PDF);
    }

    @Override
    public Mono<Void> write(Publisher<? extends Flux<DataBuffer>> publisher, ResolvableType resolvableType,
                            MediaType mediaType, ReactiveHttpOutputMessage reactiveHttpOutputMessage,
                            Map<String, Object> map) {
        Flux<? extends Flux<DataBuffer>> flux = Flux.from((Publisher<? extends Flux<DataBuffer>>) publisher);
        return reactiveHttpOutputMessage.writeWith((Publisher<? extends DataBuffer>) flux);
    }
}