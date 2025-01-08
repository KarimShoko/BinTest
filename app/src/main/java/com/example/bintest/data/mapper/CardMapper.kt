package com.example.cryptoapp.data.mapper

import com.example.bintest.data.database.BankDbModel
import com.example.bintest.data.database.CardInfoDbModel
import com.example.bintest.data.database.CountryDbModel
import com.example.bintest.data.database.NumberDbModel
import com.example.bintest.data.network.model.BankDto
import com.example.bintest.data.network.model.CardInfoDto
import com.example.bintest.data.network.model.CountryDto
import com.example.bintest.data.network.model.NumberDto
import com.example.bintest.domain.Bank
import com.example.bintest.domain.CardInfo
import com.example.bintest.domain.Country
import com.example.bintest.domain.Number


class CardMapper {

    fun mapListDbModelToListEntity(list: List<CardInfoDbModel>): List<CardInfo> {
        return list.map { mapDbModelToEntity(it) }
    }

    fun mapDtoToDbModel(dto: CardInfoDto, binNumber: String): CardInfoDbModel {
        return CardInfoDbModel(
            id = 0,
            binNumber = binNumber.toInt(),
            scheme = dto.scheme,
            type = dto.type,
            brand = dto.brand,
            country = mapCountryDtoToDbModel(dto.country),
            bank = mapBankDtoToDbModel(dto.bank),
            number = mapNumberDtoToDbModel(dto.number)
        )
    }

    fun mapDtoToEntity(dto: CardInfoDto, binNumber: String): CardInfo {
        return CardInfo(
            binNumber = binNumber.toInt(),
            scheme = dto.scheme,
            type = dto.type,
            brand = dto.brand,
            country = mapCountryDtoToEntity(dto.country),
            bank = mapBankDtoToDbEntity(dto.bank),
            number = mapNumberDtoToDbEntity(dto.number)
        )
    }

    fun mapDbModelToEntity(dbModel: CardInfoDbModel): CardInfo {
        return CardInfo(
            binNumber = dbModel.binNumber,
            scheme = dbModel.scheme,
            type = dbModel.type,
            brand = dbModel.brand,
            country = mapCountryDbModelEntity(dbModel.country),
            bank = mapBankDbModelToEntity(dbModel.bank),
            number = mapNumberDbModelToEntity(dbModel.number)
        )
    }

    fun mapCountryDbModelEntity(countryDbModel: CountryDbModel): Country {
        return Country(
            name = countryDbModel.name,
            currency = countryDbModel.currency,
            longitude = countryDbModel.longitude,
            latitude = countryDbModel.latitude
        )
    }

    fun mapBankDbModelToEntity(bankDbModel: BankDbModel): Bank {
        return Bank(
            name = bankDbModel.name,
            url = bankDbModel.url,
            phone = bankDbModel.phone,
            city = bankDbModel.city
        )
    }

    fun mapNumberDbModelToEntity(numberDbModel: NumberDbModel?): com.example.bintest.domain.Number {
        return Number(
            length = numberDbModel?.length,
            luhn = numberDbModel?.luhn
        )
    }

    fun mapCountryDtoToDbModel(countryDto: CountryDto): CountryDbModel {
        return CountryDbModel(
            name = countryDto.name,
            currency = countryDto.currency,
            longitude = countryDto.longitude,
            latitude = countryDto.latitude
        )
    }

    fun mapCountryDtoToEntity(countryDto: CountryDto): Country {
        return Country(
            name = countryDto.name,
            currency = countryDto.currency,
            longitude = countryDto.longitude,
            latitude = countryDto.latitude
        )
    }

    fun mapBankDtoToDbModel(bankDto: BankDto): BankDbModel {
        return BankDbModel(
            name = bankDto.name,
            url = bankDto.url,
            phone = bankDto.phone,
            city = bankDto.city
        )
    }

    fun mapBankDtoToDbEntity(bankDto: BankDto): Bank {
        return Bank(
            name = bankDto.name,
            url = bankDto.url ?: "Unkown bank url",
            phone = bankDto.phone ?: "Unkown bank phone ",
            city = bankDto.city ?: "Unkown bank city"
        )
    }

    fun mapNumberDtoToDbModel(numberDto: NumberDto): NumberDbModel {
        return NumberDbModel(
            length = numberDto.length,
            luhn = numberDto.luhn
        )
    }

    fun mapNumberDtoToDbEntity(numberDto: NumberDto): Number {
        return Number(
            length = numberDto.length,
            luhn = numberDto.luhn
        )
    }
}