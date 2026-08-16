# Mon Premier Point GitLab

Projet Maven (`mon-app`) accompagné d'un pipeline CI/CD complet construit dans le cadre d'une formation CI/CD, couvrant GitLab CI et Jenkins.

## 🏗️ Architecture du pipeline

Le pipeline `.gitlab-ci.yml` déroule 6 stages, du commit jusqu'à la production :

```
Build → Test → Security → Deploy Staging → API Tests → Deploy Prod
```

| Stage | Rôle | Outils |
|---|---|---|
| **Build** | Compile le code, produit le JAR | Maven |
| **Test** | Tests unitaires + couverture | JUnit 5, JaCoCo |
| **Security** | Analyse statique (non bloquante) | Semgrep |
| **Deploy Staging** | Déploiement automatique en pré-prod | — |
| **API Tests** | Smoke tests post-déploiement | Newman (Postman) |
| **Deploy Prod** | Déploiement en production, **validation manuelle requise** | — |

## 🔒 Principe clé : l'immutabilité

Le pipeline ne déploie jamais `latest`. Chaque déploiement est tagué avec le SHA du commit :

```yaml
variables:
  IMAGE_TAG: "$CI_COMMIT_SHA"
```

Résultat : chaque déploiement en staging ou en production est traçable à un commit précis et unique — utile pour diagnostiquer un incident ou faire un rollback ciblé.

## ✋ Le gate manuel

Le déploiement en production nécessite une validation humaine explicite (`when: manual`). Tous les stages précédents s'exécutent automatiquement à chaque push sur `main` ; la mise en production reste, elle, une décision consciente.

## 🔧 Intégration Jenkins

Ce projet dispose également d'un `Jenkinsfile` déclaratif équivalent, illustrant les correspondances entre GitLab CI et Jenkins (stages, artefacts, rapports JUnit, agents Docker par stage).

## 📚 Formation

Ce repo a servi de support pratique à une formation CI/CD en 6 modules : fondamentaux GitLab CI, directives avancées (DAG, environments), runners, tests automatisés (JUnit, REST Assured, Newman), Jenkins, et pipeline de production complet.
