package net.scit.hw_cashbook.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.hw_cash.dto.CashBookDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@Table(name="cashbook")
public class CashBookEntity {
	@Id
	@Column(name="cash_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cashSeq;
	
	@Column(name="io_type")
	private Boolean ioType;
	
	@Column(name="memo")
	private String memo;
	
	@Column(name="amount")
	private Integer amount;
	
	@Column(name="regdate")
	@CreationTimestamp
	private LocalDate regdate;
	
	public static CashBookEntity toEntity(CashBookDTO cashBookDTO) {
		return CashBookEntity.builder()
				.cashSeq(cashBookDTO.getCashSeq())
				.ioType(cashBookDTO.getIoType())
				.memo(cashBookDTO.getMemo())
				.amount(cashBookDTO.getAmount())
				.regdate(cashBookDTO.getRegdate())
				.build();
	}
}
