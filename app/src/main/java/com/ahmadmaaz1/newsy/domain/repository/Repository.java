package com.ahmadmaaz1.newsy.domain.repository;

import androidx.paging.PagingData;

import com.ahmadmaaz1.newsy.domain.model.Article;

import java.util.List;

import kotlinx.coroutines.flow.Flow;

public interface Repository {
    Flow<PagingData<Article>> getNews(List<String> sources);

}
