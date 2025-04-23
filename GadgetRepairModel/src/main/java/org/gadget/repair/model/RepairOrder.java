package org.gadget.repair.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RepairOrder {

    private UUID id;

    private Gadget gadget;

    private Customer customer;

    private LocalDateTime localDateTime;

    private String problemDescription;

}
