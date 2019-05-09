package net.librec;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * @author LuZhong
 * @date 2019/3/14 09:37
 * @description
 */
public class Word2VecCase {
    private static Logger log = LoggerFactory.getLogger(Word2VecCase.class);

    public static void main(String[] args) throws IOException {
        String filePath = "D:\\Code\\Python\\NLP\\output_word2vec\\seg_words.txt";
        SentenceIterator iter = new BasicLineIterator(new File(filePath));
        TokenizerFactory t = new DefaultTokenizerFactory();
        t.setTokenPreProcessor(new CommonPreprocessor());

        Word2Vec vec = new Word2Vec.Builder()
                .minWordFrequency(5)
                .iterations(1)
                .layerSize(200)
                .minWordFrequency(2)
                .seed(42)
                .windowSize(5)
                .iterate(iter)
                .build();
        vec.fit();

        // Write word vectors to file
        WordVectorSerializer.writeWord2VecModel(vec, "pathToWriteto.txt");

    }

}
