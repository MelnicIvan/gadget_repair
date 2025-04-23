package org.gadget.repair.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gadget.repair.enums.GadgetType;
import org.gadget.repair.enums.RepairStatus;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Gadget {

    private UUID id;

    private String name;

    private GadgetType gadgetType;

    private RepairStatus repairStatus;

}
