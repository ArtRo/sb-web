package com.example.demo.api.es;

import com.example.demo.bo.es.ESBasebo;
import com.google.gson.Gson;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by dhf_ndm on 2019/5/7
 * the previous bug derived from the previous
 */
@Service
public class ESApi {

    @Qualifier("highLevelClient")
    @Autowired
    RestHighLevelClient client;

    @Autowired
    Gson gson;

    public Integer insertEs(ESBasebo esBasebo) throws IOException {
        IndexRequest indexRequest = new IndexRequest(esBasebo.getIndex(), esBasebo.getType(), esBasebo.getId());
        indexRequest.source(gson.toJson(esBasebo.getDocument()), XContentType.JSON);
        IndexResponse index = client.index(indexRequest);
        return index.getShardInfo().getSuccessful();
    }

    public Integer updateEs(ESBasebo esBasebo) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest(esBasebo.getIndex(), esBasebo.getType(), esBasebo.getId());
        updateRequest.doc(esBasebo.getDocument());
        UpdateResponse update = client.update(updateRequest);
        return update.status().getStatus();
    }

    public Object getIndexFromEs(ESBasebo esBasebo) throws IOException {
        GetRequest getRequest = new GetRequest(esBasebo.getIndex(), esBasebo.getType(), esBasebo.getId());
        getRequest.storedFields(esBasebo.getStoreFields());
        FetchSourceContext fetchSourceContext = new FetchSourceContext(true, esBasebo.getIncludes(), esBasebo.getExcludes());
        getRequest.fetchSourceContext(fetchSourceContext);
        GetResponse documentFields = client.get(getRequest);
        return documentFields;
    }

    public Integer deleteEs(ESBasebo esBasebo) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest(esBasebo.getIndex(), esBasebo.getType(), esBasebo.getId());
        DeleteResponse delete = client.delete(deleteRequest);
        return delete.status().getStatus();
    }

    public Object searchEs(ESBasebo esBasebo) throws IOException {
        SearchRequest searchRequest = new SearchRequest(esBasebo.getIndex());
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());

        searchRequest.source(searchSourceBuilder);
        SearchResponse search = client.search(searchRequest);
        return search.getHits();
    }

}
