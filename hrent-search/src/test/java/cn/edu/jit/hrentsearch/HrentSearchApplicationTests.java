package cn.edu.jit.hrentsearch;

import lombok.extern.slf4j.Slf4j;
import net.librec.common.LibrecException;
import net.librec.conf.Configuration;
import net.librec.data.model.TextDataModel;
import net.librec.eval.EvalContext;
import net.librec.eval.RecommenderEvaluator;
import net.librec.eval.ranking.NormalizedDCGEvaluator;
import net.librec.recommender.Recommender;
import net.librec.recommender.RecommenderContext;
import net.librec.recommender.cf.ItemKNNRecommender;
import net.librec.similarity.CosineSimilarity;
import net.librec.similarity.RecommenderSimilarity;
import org.junit.Test;

@Slf4j
public class HrentSearchApplicationTests {

    @Test
    public void librec() throws LibrecException {
        // build data model
        Configuration conf = new Configuration();
        Configuration.Resource resource = new Configuration.Resource("bhfree.properties");
        conf.addResource(resource);
        TextDataModel dataModel = new TextDataModel(conf);
        dataModel.buildDataModel();

        // build recommender context
        RecommenderContext context = new RecommenderContext(conf, dataModel);

        // build similarity
        conf.set("rec.recommender.similarity.key" ,"item");
        conf.setBoolean("rec.recommender.isranking", true);
        conf.setInt("rec.similarity.shrinkage", 10);
        RecommenderSimilarity similarity = new CosineSimilarity();
        similarity.buildSimilarityMatrix(dataModel);
        context.setSimilarity(similarity);

        // build recommender
        conf.set("rec.neighbors.knn.number", "200");
        Recommender recommender = new ItemKNNRecommender();
        recommender.setContext(context);

        // run recommender algorithm
        recommender.train(context);

        // evaluate the recommended result
        EvalContext evalContext = new EvalContext(conf, recommender, dataModel.getTestDataSet(), context.getSimilarity().getSimilarityMatrix(), context.getSimilarities());
        RecommenderEvaluator ndcgEvaluator = new NormalizedDCGEvaluator();
        ndcgEvaluator.setTopN(10);
        double ndcgValue = ndcgEvaluator.evaluate(evalContext);
        log.info("ndcg:{}", ndcgValue);
//        recommender.getRecommendedList(recommender.recommendRank())
//                .forEach(rec -> log.info("{} {} {}", rec.getUserId(), rec.getItemId(), rec.getValue()));

    }

}

