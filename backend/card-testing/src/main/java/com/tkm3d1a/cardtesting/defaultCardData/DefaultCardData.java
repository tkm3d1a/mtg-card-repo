package com.tkm3d1a.cardtesting.defaultCardData;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Calendar;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class DefaultCardData {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "insertedDTM", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Calendar insertedDTM;

    @Column(name = "fileData", nullable = false, updatable = false, length = 10000000)
    @Lob
    private byte[] fileData;

    private String type;
    private String name;
}
