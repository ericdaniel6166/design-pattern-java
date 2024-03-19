package com.eric6166.Behavioral.ChainOfResponsibility.Problem;

public class Problem {

    public static class WebCrawler {
        void crawl(String url) {
            System.out.println(String.format("a simple proces of crawling a url: %s", url));
            System.out.println("- Check the url");
            System.out.println("- Fetch url content");
            System.out.println("- Extract information from content");
            System.out.println("- Save information to database");
            // problem: new logic -> modify crawl()
        }
    }

    public static void main(String[] args) {
        var crawler = new WebCrawler();
        crawler.crawl("http://localhost/some-page");

    }
}
