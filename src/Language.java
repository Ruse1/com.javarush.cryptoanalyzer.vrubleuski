public enum Language {
    RUS ("Русский"),
    ENG ("Английский"),
    DEFAULT ("По умолчанию");
    private String name;
    Language(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
