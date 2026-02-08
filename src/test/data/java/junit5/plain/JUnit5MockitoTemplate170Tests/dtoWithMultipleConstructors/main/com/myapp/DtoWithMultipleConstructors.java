/*
 * Copyright 2026 Squaretest LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.myapp;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

/**
 * Based on class uk.gov.digital.ho.hocs.casework.domain.model.CaseData from
 * https://github.com/UKHomeOffice/hocs-casework. The software is released under the MIT License.
 */
@Entity
public class DtoWithMultipleConstructors {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "otherId", columnDefinition ="otherId")
    private String otherId;

    @Getter
    @Column(name = "created")
    private LocalDateTime created = LocalDateTime.now();

    @Getter
    @Column(name = "type")
    private String type;

    @Getter
    @Column(name = "reference")
    private String reference;

    @Setter
    @Getter
    @Column(name = "deleted")
    private boolean deleted;

    @Getter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "primary_topic_otherId", referencedColumnName = "otherId", insertable = false, updatable = false)
    private Topic primaryTopic;


    public DtoWithMultipleConstructors() {
    }

    public DtoWithMultipleConstructors(final Long id, final String otherId, final LocalDateTime created, final String type) {
        this.id = id;
        this.otherId = otherId;
        this.created = created;
        this.type = type;
    }

    public DtoWithMultipleConstructors(final Long id, final String otherId, final LocalDateTime created, final String type, final String reference) {
        this.id = id;
        this.otherId = otherId;
        this.created = created;
        this.type = type;
        this.reference = reference;
    }

    public DtoWithMultipleConstructors(final Long id, final String otherId, final LocalDateTime created, final String type, final String reference, final boolean deleted) {
        this.id = id;
        this.otherId = otherId;
        this.created = created;
        this.type = type;
        this.reference = reference;
        this.deleted = deleted;
    }

    public DtoWithMultipleConstructors(final Long id, final String otherId, final LocalDateTime created, final String type, final String reference, final boolean deleted, final Topic primaryTopic) {
        this.id = id;
        this.otherId = otherId;
        this.created = created;
        this.type = type;
        this.reference = reference;
        this.deleted = deleted;
        this.primaryTopic = primaryTopic;
    }
}
