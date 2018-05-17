package com.wang.dto;

import com.wang.util.MyBuilder;

/**
 * DTO :Diary图片对象
 * <p>
 * 构建了可变属性集，默认{名称,文本,日期}
 * 实现comparable接口，可根据上传日期date进行排序
 * 覆盖equals() hashCode()
 *
 * @auther ten
 */
public class DiaryDTO implements Comparable<DiaryDTO> {

    private final int diaryId;
    private final String diaryName;
    private final String diaryText;
    private final String diaryDate;

    /**
     * hashCode缓存
     */
    private volatile int hashCode;

    private DiaryDTO(DiaryDTO.Builder builder) {
        diaryId = builder.diaryId;
        diaryName = builder.diaryName;
        diaryText = builder.diaryText;
        diaryDate = builder.diaryDate;
    }

    public static class Builder implements MyBuilder<DiaryDTO> {

        private final int diaryId;

        private String diaryText = "天气晴朗";
        private String diaryName = "周日, 晴";
        private String diaryDate = "2018-05-20";

        public Builder(int diaryId) {
            this.diaryId = diaryId;
        }

        public Builder diaryText(String val) {
            diaryText = val;
            return this;
        }

        public Builder diaryName(String val) {
            diaryName = val;
            return this;
        }

        public Builder diaryDate(String val) {
            diaryDate = val;
            return this;
        }

        @Override
        public DiaryDTO build() {
            return new DiaryDTO(this);
        }
    }

    /**
     * compareTo: Comparable
     * <p>
     * 根据diaryDate进行比较
     * <p>
     * 1. 小于 负数
     * 2. 等于 零
     * 3. 大于 正数
     *
     * @param diary diary
     * @return this-other ? 负 : (this-other ? 0 : 正)
     */
    @Override
    public int compareTo(DiaryDTO diary) {
        return Integer.valueOf(this.diaryDate) - Integer.valueOf(diary.getDiaryDate());
    }


    @Override
    public boolean equals(Object obj) {
        //引用检测
        if (obj == this) {
            return true;
        }
        //类型检测
        if (!(obj instanceof DiaryDTO)) {
            return false;
        }
        //参数检测
        if (this.diaryId != ((DiaryDTO) obj).getDiaryId()) {
            return false;
        }
        if (!this.diaryName.equals(((DiaryDTO) obj).getDiaryName())) {
            return false;
        }
        if (!this.diaryText.equals(((DiaryDTO) obj).getDiaryText())) {
            return false;
        }
        if (!this.diaryDate.equals(((DiaryDTO) obj).getDiaryDate())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            result = 17;
            result = 31 * result + diaryId;
            result = 31 * result + diaryName.hashCode();
            result = 31 * result + diaryText.hashCode();
            result = 31 * result + diaryDate.hashCode();
            hashCode = result;
        }
        return result;
    }

    //Diary@1234{name:'',text:'',date:''}
    @Override
    public String toString() {
        return "Diary@" + diaryId + "{name:" + diaryName + ",text:"
                + diaryText + ",date:" + diaryDate + "}";
    }

    //getter
    public int getDiaryId() {
        return diaryId;
    }

    public String getDiaryName() {
        return diaryName;
    }

    public String getDiaryText() {
        return diaryText;
    }

    public String getDiaryDate() {
        return diaryDate;
    }

}
