//package com.mobile.vnews;
//
//import org.junit.Test;
//
//import java.util.*;
//
//import edu.stanford.nlp.ling.CoreAnnotations;
//import edu.stanford.nlp.ling.CoreLabel;
//import edu.stanford.nlp.pipeline.*;
//import edu.stanford.nlp.semgraph.SemanticGraph;
//import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
//import edu.stanford.nlp.trees.Tree;
//import edu.stanford.nlp.trees.TreeCoreAnnotations;
//import edu.stanford.nlp.util.CoreMap;
//import edu.stanford.nlp.util.PropertiesUtils;
//
///**
// * Created by xuantang on 12/22/17.
// */
//
//public class LemmaTest {
//    /**
//     *
//     * @param word
//     */
//    public static void getLemma(String str) {
//        // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution
//        StanfordCoreNLP pipeline = new StanfordCoreNLP(
//                PropertiesUtils.asProperties(
//                        "annotators", "tokenize,ssplit,pos,lemma,parse,natlog",
//                        "ssplit.isOneSentence", "true",
//                        "parse.model", "edu/stanford/nlp/models/srparser/englishSR.ser.gz",
//                        "tokenize.language", "en"));
//
//
//        // read some text in the text variable
//        String text = str;
//
//        // create an empty Annotation just with the given text
//        Annotation document = new Annotation(text);
//
//        // run all Annotators on this text
//        pipeline.annotate(document);
//
//        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
//
//        for(CoreMap sentence: sentences) {
//            // traversing the words in the current sentence
//            // a CoreLabel is a CoreMap with additional token-specific methods
//            for (CoreLabel token: sentence.get(CoreAnnotations.TokensAnnotation.class)) {
//                // this is the text of the token
//                String word = token.get(CoreAnnotations.TextAnnotation.class);
//                // this is the POS tag of the token
//                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
//                // this is the NER label of the token
//                String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
//            }
//
//            // this is the parse tree of the current sentence
//            Tree tree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
//
//            // this is the Stanford dependency graph of the current sentence
//            SemanticGraph dependencies = sentence.get(SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation.class);
//        }
//
////        Document doc = new Document(word);
////        for (Sentence sent : doc.sentences()) {  // Will iterate over two sentences
////            // We're only asking for words -- no need to load any models yet
////            System.out.println(sent.word(0));
////            // When we ask for the lemma, it will load and run the part of speech tagger
////            System.out.println(sent.lemma(0));
////            // When we ask for the parse, it will load and run the parser
////            System.out.println(sent.parse());
////            // ...
////        }
//    }
//
//    @Test
//    public void getLemma() {
//        getLemma("sentences and");
//    }
//}
