package net.scit.hw_cash.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.hw_cashbook.entity.CashBookEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CashBookDTO {
	private Long cashSeq;
	private Boolean ioType;
	private String memo;
	private Integer amount;
	private LocalDate regdate;
	
	public static CashBookDTO toDTO(CashBookEntity cashBookEntity) {
		return CashBookDTO.builder()
				.cashSeq(cashBookEntity.getCashSeq())
				.ioType(cashBookEntity.getIoType())
				.memo(cashBookEntity.getMemo())
				.amount(cashBookEntity.getAmount())
				.regdate(cashBookEntity.getRegdate())
				.build();
	}
}
