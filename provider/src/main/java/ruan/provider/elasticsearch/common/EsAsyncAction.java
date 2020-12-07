package ruan.provider.elasticsearch.common;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.BulkByScrollTask.Status;
import org.springframework.stereotype.Component;
import ruan.provider.common.ServerException;

@Slf4j
@Component
public class EsAsyncAction implements ActionListener<BulkByScrollResponse> {

    @Override
    public void onResponse(BulkByScrollResponse response) {
        Status status = response.getStatus();
        long total = status.getTotal();
        log.info("累计操作成功:{}条", total);
    }

    @SneakyThrows
    @Override
    public void onFailure(Exception e) {
        log.error("操作es异常:{}", e);
        throw new ServerException("操作异常！");
    }
}
