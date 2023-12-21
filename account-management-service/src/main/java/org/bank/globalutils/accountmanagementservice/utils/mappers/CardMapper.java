package org.bank.globalutils.accountmanagementservice.utils.mappers;

import org.bank.globalutils.accountmanagementservice.dto.CardCreationDto;
import org.bank.globalutils.accountmanagementservice.dto.CardResponseDto;
import org.bank.globalutils.accountmanagementservice.models.Card;
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
