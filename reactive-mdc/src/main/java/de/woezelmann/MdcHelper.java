package de.woezelmann;

import org.slf4j.MDC;
import reactor.core.publisher.Signal;

import java.util.Optional;
import java.util.function.Consumer;

public class MdcHelper {
    public static final String X_FLOW_ID_CONTEXT_KEY = "x-flow-id";
    public static final String X_FLOW_ID_MDC_KEY = "flowId";
    public static <T> Consumer<Signal<T>> logOnNext(Consumer<T> logStatement) {
        return signal -> {
            if (!signal.isOnNext()) return;
            Optional<String> toPutInMdc = signal.getContextView().getOrEmpty(X_FLOW_ID_CONTEXT_KEY);

            toPutInMdc.ifPresentOrElse(tpim -> {
                        try (MDC.MDCCloseable ignored = MDC.putCloseable(X_FLOW_ID_MDC_KEY, tpim)) {
                            logStatement.accept(signal.get());
                        }
                    },
                    () -> logStatement.accept(signal.get()));
        };
    }
}
