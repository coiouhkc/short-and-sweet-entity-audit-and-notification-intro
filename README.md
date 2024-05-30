---
marp: true
title: Short and sweet
description: Short and sweet
theme: uncover
paginate: true
_paginate: false

---

# Short and sweet

## Intro to Entity Audit

---

# Intro, reason & background

Who changed what when (and why?).

* createdBy/changedBy
* createdAt/changedAt
* (comment?)
* diff

---

# Intro, reason & background (contd.)

How?

* app level
* lib level
* database table/row level
* database system/log/file level

---

# Who

```
@Context
SecurityContext securityContext;
...

private String getPrincipal() {
    return (securityContext != null && securityContext.getUserPrincipal() != null) ? securityContext.getUserPrincipal().getName() : "anonymous";
  }
```

---

# What

* JPA Entity fields
  * `Object[] old, Object[] new`
  * `ROW(OLD.*), ROW(NEW.*)`

---

# When

* Application code
* JPA Listener
* Hibernate Interceptor
* Database (trigger)
* Database (CDC, Debezium)

---

# Demo

---

# Links

* https://vladmihalcea.com/how-to-audit-entity-modifications-using-the-jpa-entitylisteners-embedded-and-embeddable-annotations/
* https://vladmihalcea.com/the-best-way-to-implement-an-audit-log-using-hibernate-envers/
* https://vladmihalcea.com/a-beginners-guide-to-cdc-change-data-capture/
* https://wiki.postgresql.org/wiki/Audit_trigger
* https://vladmihalcea.com/mysql-audit-logging-triggers/
* https://vladmihalcea.com/how-to-emulate-createdby-and-lastmodifiedby-from-spring-data-using-the-generatortype-hibernate-annotation/

---

# Q&A