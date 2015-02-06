package decoder;

import messages.ElkResponse;

/**
 * Created by pborges on 1/22/15.
 */
public interface ElkDecoder<T extends ElkResponse> {
    public T decode(String data);
}
