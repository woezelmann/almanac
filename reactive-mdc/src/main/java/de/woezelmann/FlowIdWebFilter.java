package de.woezelmann;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import static de.woezelmann.MdcHelper.X_FLOW_ID_CONTEXT_KEY;

@Component
public class FlowIdWebFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        final String flowId = exchange.getRequest().getHeaders().getFirst("x-flow-id");

        return chain.filter(exchange)
                .contextWrite(context -> context.put(X_FLOW_ID_CONTEXT_KEY, flowId));
    }
}
