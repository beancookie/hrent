package cn.edu.jit.hrentrec.librec;


import net.librec.common.LibrecException;
import net.librec.conf.Configuration;
import net.librec.data.DataModel;
import net.librec.data.model.TextDataModel;
import net.librec.eval.EvalContext;
import net.librec.eval.RecommenderEvaluator;
import net.librec.eval.ranking.NormalizedDCGEvaluator;
import net.librec.eval.rating.MAEEvaluator;
import net.librec.filter.GenericRecommendedFilter;
import net.librec.filter.RecommendedFilter;
import net.librec.job.RecommenderJob;
import net.librec.recommender.Recommender;
import net.librec.recommender.RecommenderContext;
import net.librec.recommender.cf.ItemKNNRecommender;
import net.librec.recommender.cf.UserKNNRecommender;
import net.librec.recommender.content.ConvMFRecommender;
import net.librec.recommender.item.RecommendedItem;
import net.librec.recommender.nn.rating.AutoRecRecommender;
import net.librec.similarity.CosineSimilarity;
import net.librec.similarity.PCCSimilarity;
import net.librec.similarity.RecommenderSimilarity;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @Auther: LuZhong
 * @Date: 2019/2/18 17:32
 * @Description:
 */
public class LibrecTest {

    @Test
    public void testCommend() throws Exception {
        // build data model
        Configuration conf = new Configuration();
        Configuration.Resource resource = new Configuration.Resource("rec/content/convmf-test.properties");
        conf.addResource(resource);
        TextDataModel dataModel = new TextDataModel(conf);
        dataModel.buildDataModel();

        // build recommender context
        RecommenderContext context = new RecommenderContext(conf, dataModel);

        // build similarity
        conf.set("rec.recommender.similarity.key", "item");
        conf.setBoolean("rec.recommender.isranking", true);
        conf.setInt("rec.similarity.shrinkage", 10);
        RecommenderSimilarity similarity = new CosineSimilarity();
        similarity.buildSimilarityMatrix(dataModel);
        context.setSimilarity(similarity);

        // build recommender
        Recommender recommender = new ConvMFRecommender();
        recommender.setContext(context);

        // run recommender algorithm
        recommender.train(context);

        // evaluate the recommended result
        EvalContext evalContext = new EvalContext(conf, recommender, dataModel.getTestDataSet(), context.getSimilarity().getSimilarityMatrix(), context.getSimilarities());
        RecommenderEvaluator ndcgEvaluator = new MAEEvaluator();
        ndcgEvaluator.setTopN(10);
        double ndcgValue = ndcgEvaluator.evaluate(evalContext);
        System.out.println("ndcg:" + ndcgValue);

    }

    @Test
    public void testTopicmfmt() throws LibrecException, IOException, ClassNotFoundException {
        Configuration conf = new Configuration();
        Configuration.Resource resource = new Configuration.Resource("rec/content/topicmfmt-test.properties");
        conf.addResource(resource);
        RecommenderJob job = new RecommenderJob(conf);
        job.runJob();
    }

    @Test
    public void testTopicmfat() throws LibrecException, IOException, ClassNotFoundException {
        Configuration conf = new Configuration();
        Configuration.Resource resource = new Configuration.Resource("rec/content/topicmfat-test.properties");
        conf.addResource(resource);
        RecommenderJob job = new RecommenderJob(conf);
        job.runJob();
    }

    @Test
    public void testConvmf() throws LibrecException, IOException, ClassNotFoundException {
        Configuration conf = new Configuration();
        Configuration.Resource resource = new Configuration.Resource("rec/content/convmf-test.properties");
        conf.addResource(resource);
        RecommenderJob job = new RecommenderJob(conf);
        job.runJob();
    }

    @Test
    public void testAutorec() throws LibrecException, IOException, ClassNotFoundException {
        System.out.println(System.getenv());
        Configuration conf = new Configuration();
        Configuration.Resource resource = new Configuration.Resource("rec/nn/rating/autorec-test.properties");
        conf.addResource(resource);
        RecommenderJob job = new RecommenderJob(conf);
        job.runJob();
    }

    @Test
    public void testRankgeofm() throws LibrecException, IOException, ClassNotFoundException {
        Configuration conf = new Configuration();
        Configuration.Resource resource = new Configuration.Resource("rec/poi/rankgeofm.properties");
        conf.addResource(resource);
        RecommenderJob job = new RecommenderJob(conf);
        job.runJob();
    }

}
