package com.example.emsp.api.model.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class PageResult<T> {

    private Page page;

    private List<T> rows;

    @Data
    @RequiredArgsConstructor
    public static class Page {

        private int number;
        private int size;
        private long totalElements;

        public Page(int pageNumber, int pageSize, long totalElements) {
            this.number = pageNumber;
            this.size = pageSize;
            this.totalElements = totalElements;
        }
    }

}
