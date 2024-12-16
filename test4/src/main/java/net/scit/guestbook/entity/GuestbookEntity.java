package net.scit.guestbook.entity;

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
import net.scit.guestbook.dto.GuestbookDTO;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder

@Entity
@Table(name="guestbook")
public class GuestbookEntity {
	@Id
	@Column(name="seqno")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer seqno;
	
	@Column(name="guest_name", nullable = false)
	private String guest_name;
	
	@Column(name="guest_pwd", nullable = false)
	private String guest_pwd;
	
	@Column(name="content", nullable = false)
	private String content;
	
	@Column(name="regdate")
	@CreationTimestamp
	private LocalDate regdate;
	
	public static GuestbookEntity toEntity(GuestbookDTO guestbookdto) {
		return GuestbookEntity.builder()
				.seqno(guestbookdto.getSeqno())
				.guest_name(guestbookdto.getGuest_name())
				.guest_pwd(guestbookdto.getGuest_pwd())
				.content(guestbookdto.getContent())
				.regdate(guestbookdto.getRegdate())
				.build();
	}
}
