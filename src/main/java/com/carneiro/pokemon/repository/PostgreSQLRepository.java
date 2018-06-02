package com.carneiro.pokemon.repository;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.hql.internal.ast.ASTQueryTranslatorFactory;
import org.hibernate.hql.spi.QueryTranslator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

import static java.util.Collections.EMPTY_MAP;

@Repository
public class PostgreSQLRepository {

    private final static String QUESTION_CHAR = "?";
    private static final String EMPTY_STRING = "";
    private static final String SQL_FROM = " from ";
    private static final String VARCHAR = "'%s'";
    private static final String POSTGRES_COUNT_OVER_FROM = " , count(*) OVER() as size from ";

    private final Logger logger = LoggerFactory.getLogger(PostgreSQLRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    public String getSQLWithRowCount(TypedQuery<?> typedQuery, List<Object> params) {
        String sql = getSQL(typedQuery, params);
        return StringUtils.replaceOnce(sql, SQL_FROM, POSTGRES_COUNT_OVER_FROM);
    }

    private String getSQL(TypedQuery<?> typedQuery, List<Object> params) {
        String rawSQL = getSQLWithNoArgs(typedQuery).toLowerCase();

        final int sqlNumberArgs = StringUtils.countMatches(rawSQL, QUESTION_CHAR);
        if (sqlNumberArgs > 0) {

            final int receivedNumberArgs = params.size();
            if (sqlNumberArgs != receivedNumberArgs) {
                throw new IllegalArgumentException(
                    String.format(
                        "Wrong number of arguments required '%d', got '%d'", sqlNumberArgs, receivedNumberArgs
                    )
                );
            }

            for (Object p : params) {
                String value = String.format(VARCHAR, p.toString());
                rawSQL = StringUtils.replaceOnce(rawSQL, QUESTION_CHAR, value);
            }
        }

        return rawSQL;
    }

    private String getSQLWithNoArgs(TypedQuery<?> typedQuery) {
        final String hqlQueryText = typedQuery.unwrap(org.hibernate.query.Query.class).getQueryString();

        final ASTQueryTranslatorFactory translatorFactory = new ASTQueryTranslatorFactory();
        final SessionImplementor hibernateSession = entityManager.unwrap(SessionImplementor.class);
        final QueryTranslator translator = translatorFactory.createQueryTranslator(EMPTY_STRING, hqlQueryText, java.util.Collections.EMPTY_MAP, hibernateSession.getFactory(), null);

        translator.compile(EMPTY_MAP, false);
        return translator.getSQLString();
    }

}
