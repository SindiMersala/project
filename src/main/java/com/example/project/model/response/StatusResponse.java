package com.example.project.model.response;

import com.example.project.model.Status;
import lombok.AllArgsConstructor;
    @AllArgsConstructor

    public class StatusResponse {
        public final long id;
        public final long userId;
        public final String email;
        public final long vaccineId;
        public final String vaccineName;
        public final String accept;

        public static StatusResponse fromEntity(Status s) {
            return new StatusResponse(
                    s.getId(),
                    s.getUser().getId(),
                    s.getUser().getEmail(),
                    s.getVaccine().getId(),
                    s.getVaccine().getType(),
                    s.getAccept()
            );
        }
}
