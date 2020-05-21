package com.zhangyfvir.jarticle.articleservice.component.markdown;


import reactor.util.annotation.Nullable;

public class MarkdownHelper {
    private static final IMarkdownParser markdownParser = new FlexmarkImpl();

    @Nullable
    public static String parse(@Nullable String content) {
        return markdownParser.parse(content);
    }

}
