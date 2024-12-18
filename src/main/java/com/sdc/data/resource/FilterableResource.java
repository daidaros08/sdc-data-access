package com.sdc.data.resource;

import java.util.List;
import java.util.Map;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * This class allow to retrieve params to filter the paginated search
 *
 */


public class FilterableResource extends BaseResource {

    @Context
    private UriInfo uriInfo;

    protected static final String OFFSET = "offset";
    protected static final String LIMIT = "limit";
    protected static final String SORT = "sort";
    protected static final String ORDER = "order";
    private static final List<String> PAGINATION_FILTERS = Lists.newArrayList(OFFSET, LIMIT, SORT, ORDER);

    public Map<String, List<String>> getFilterableAttributes() {
        Map<String, List<String>> filterableAttributes = Maps.newHashMap();

        if (CollectionUtils.isNotEmpty(uriInfo.getQueryParameters().values())) {
            MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();

            queryParams.keySet().stream()
                .filter(key -> !PAGINATION_FILTERS.contains(key))
                .forEach(key -> filterableAttributes.put(key, queryParams.get(key)));
        }

        return filterableAttributes;
    }

    /**
     * Returns the filterable attributes passed as URI parameters.
     * It will avoid the pagination attributes such as "offset", "limit", "sort" and "order".
     * All remaining attributes will be considered filterable attributes.
     * This method accept parameters to search by date range:
     *
     * @param filterName     name of the date attribute to be searched for in the date range
     * @param filterNameTo   date range lower bound attribute name
     * @param filterNameFrom name of the attribute of the top of the range of dates
     * @return a map containing the filterable attributes and the values.
     */
    public Map<String, List<String>> getFilterableAttributes(String filterName, String filterNameTo, String filterNameFrom) {
        Map<String, List<String>> filterableAttributes = getFilterableAttributes();

        if (filterableAttributes.containsKey(filterNameTo) && filterableAttributes.containsKey(filterNameFrom)) {
            String dateFrom = filterableAttributes.get(filterNameFrom).get(0);
            String dateTo = filterableAttributes.get(filterNameTo).get(0);
            filterableAttributes.put(filterName, Lists.newArrayList(dateFrom, dateTo));
        }
        return filterableAttributes;
    }

    /**
     * Setter for tests.
     *
     * @param uriInfo the {@link UriInfo}
     */
    public void setUriInfo(UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }
}
