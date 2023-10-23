package org.bank.accountmanagementservice.utils.mappers;

import org.bank.accountmanagementservice.dto.CardCreationDto;
import org.bank.accountmanagementservice.dto.CardResponseDto;
import org.bank.accountmanagementservice.models.Card;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface CardMapper {

    @Mapping(source = "userAccountNumber", target = "userAccount.number")
    Card toEntity(CardCreationDto dto);
    @Mapping(target = "userAccountNumber", source = "userAccount.number")
    CardResponseDto toResponseDto(Card card);
    List<CardResponseDto> toResponseDtos(List<Card> cards);
}
