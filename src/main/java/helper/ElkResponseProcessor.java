package helper;

import message.response.ElkResponse;

/**
 * Created by pborges on 2/5/15.
 */
public interface ElkResponseProcessor<T extends ElkResponse> {
    public void processRecord(T elkResponse) throws Exception;
}
