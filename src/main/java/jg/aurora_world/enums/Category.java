package jg.aurora_world.enums;

public enum Category {
    PERSONAL_FAVORITE("개인 즐겨 찾기"),
    WORK_MATERIAL("업무 활용 자료"),
    REFERENCE_MATERIAL("참고 자료"),
    EDUCATION_AND_LEARNING("교육 및 학습 자료");

    private final String description;

    Category(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
