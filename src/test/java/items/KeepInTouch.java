package items;

public enum KeepInTouch {
    PODCAST("Talking Kotlin Podcast"),
    TALKS("Kotlin Talks"),
    FORUM("Kotlin Forum"),
    SLACK("Slack"),
    REDDIT("Reddit"),
    STACKOVERFLOW("Stack Overflow"),
    LINKEDIN("LinkedIn"),
    TWITTER("Twitter"),
    ISSUES("Issue Tracker");

    public final String elementTitle;

    KeepInTouch(String elementTitle) {
        this.elementTitle = elementTitle;
    }
}
