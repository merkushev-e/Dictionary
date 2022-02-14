package ru.gb.dictionary.Utils

import ru.gb.model.data.DataModel
import ru.gb.model.dto.SearchResultDto
import ru.gb.repository.room.HistoryEntity


fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): List<SearchResultDto> {
    val searchResult = ArrayList<SearchResultDto>()
    if (!list.isNullOrEmpty()) {
        for (entity in list) {
            searchResult.add(SearchResultDto(entity.word, null))
        }
    }
    return searchResult
}

fun convertDataModelSuccessToEntity(appState: ru.gb.model.AppState): HistoryEntity? {
    return when (appState) {
        is ru.gb.model.AppState.Success -> {
            val searchResult = appState.data
            if (searchResult.isNullOrEmpty() || searchResult[0].text.isNullOrEmpty()) {
                null
            } else {
                HistoryEntity(searchResult[0].text!!, null)
            }
        }
        else -> null
    }
}
