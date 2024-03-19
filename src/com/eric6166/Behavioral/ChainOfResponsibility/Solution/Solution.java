package com.eric6166.Behavioral.ChainOfResponsibility.Solution;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static class Context {
        private String url;
        private String content;
        private Object data;

        public Context(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public String getContent() {
            return content;
        }

        public Object getData() {
            return data;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }

    public abstract static class Handler {
        private Handler next;

        public static Handler link(Handler first, Handler... chain) {
            Handler head = first;
            for (var nextInChain : chain) {
                head.next = nextInChain;
                head = nextInChain;
            }
            return first;
        }

        public abstract boolean check(Context context);

        protected boolean checkNext(Context context) {
            if (next == null) {
                return true;
            }
            return next.check(context);
        }

    }

    public static class CheckingUrlHandler extends Handler {

        @Override
        public boolean check(Context context) {
            System.out.println(String.format("- Checking url: %s", context.getUrl()));
            return checkNext(context);
        }
    }

    public static class FetchContentHandler extends Handler {

        @Override
        public boolean check(Context context) {
            System.out.println(String.format("- Fetching content from url: %s", context.getUrl()));
            context.setContent("some content from url");
            return checkNext(context);
        }
    }

    public static class ExtractDataHandler extends Handler {

        @Override
        public boolean check(Context context) {
            System.out.println(String.format("- Extract data from content: %s", context.getContent()));
            context.setData(new HashMap<>(Map.of("title", "Apple", "price", 10.0)));
            return checkNext(context);
        }
    }

    public static class SaveDataHandler extends Handler {

        @Override
        public boolean check(Context context) {
            System.out.println(String.format("- Saving data to database: %s", context.getData()));
            return checkNext(context);
        }
    }


    public static void main(String[] args) {
        boolean check = Handler.link(
                new CheckingUrlHandler(),
                new FetchContentHandler(),
                new ExtractDataHandler(),
                new SaveDataHandler()
        ).check(new Context("abc.com"));
        System.out.println(String.format("check: %s", check));
    }
}
