The code provided has several issues and hotspots flagged by SonarQube. I will address each remark one at a time, starting with the first one.

---

### [12]: 
#### [ISSUE](java:S1118): Add a private constructor to hide the implicit public one.

The class `Postgres` has an implicit public constructor, which is unnecessary since it only contains static methods. To fix this, we will add a private constructor to prevent instantiation.

---

### Fix for [12]:
```java
private Postgres() {
    // Prevent instantiation
}
```

---

### ContentEditor for [12]:
```json
{
  "operations": [
    {
      "operation": "INSERT",
      "lineNumber": 12,
      "content": "private Postgres() {"
    },
    {
      "operation": "INSERT",
      "lineNumber": 13,
      "content": "    // Prevent instantiation"
    },
    {
      "operation": "INSERT",
      "lineNumber": 14,
      "content": "}"
    }
  ]
}
```

---

Let me know if you'd like me to proceed with the next remark or implement this fix first.
