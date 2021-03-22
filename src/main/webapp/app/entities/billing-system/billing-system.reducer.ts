import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IBillingSystem, defaultValue } from 'app/shared/model/billing-system.model';

export const ACTION_TYPES = {
  FETCH_BILLINGSYSTEM_LIST: 'billingSystem/FETCH_BILLINGSYSTEM_LIST',
  FETCH_BILLINGSYSTEM: 'billingSystem/FETCH_BILLINGSYSTEM',
  CREATE_BILLINGSYSTEM: 'billingSystem/CREATE_BILLINGSYSTEM',
  UPDATE_BILLINGSYSTEM: 'billingSystem/UPDATE_BILLINGSYSTEM',
  DELETE_BILLINGSYSTEM: 'billingSystem/DELETE_BILLINGSYSTEM',
  RESET: 'billingSystem/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IBillingSystem>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type BillingSystemState = Readonly<typeof initialState>;

// Reducer

export default (state: BillingSystemState = initialState, action): BillingSystemState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_BILLINGSYSTEM_LIST):
    case REQUEST(ACTION_TYPES.FETCH_BILLINGSYSTEM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_BILLINGSYSTEM):
    case REQUEST(ACTION_TYPES.UPDATE_BILLINGSYSTEM):
    case REQUEST(ACTION_TYPES.DELETE_BILLINGSYSTEM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_BILLINGSYSTEM_LIST):
    case FAILURE(ACTION_TYPES.FETCH_BILLINGSYSTEM):
    case FAILURE(ACTION_TYPES.CREATE_BILLINGSYSTEM):
    case FAILURE(ACTION_TYPES.UPDATE_BILLINGSYSTEM):
    case FAILURE(ACTION_TYPES.DELETE_BILLINGSYSTEM):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_BILLINGSYSTEM_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_BILLINGSYSTEM):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_BILLINGSYSTEM):
    case SUCCESS(ACTION_TYPES.UPDATE_BILLINGSYSTEM):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_BILLINGSYSTEM):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/billing-systems';

// Actions

export const getEntities: ICrudGetAllAction<IBillingSystem> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_BILLINGSYSTEM_LIST,
    payload: axios.get<IBillingSystem>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IBillingSystem> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_BILLINGSYSTEM,
    payload: axios.get<IBillingSystem>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IBillingSystem> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_BILLINGSYSTEM,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IBillingSystem> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_BILLINGSYSTEM,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IBillingSystem> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_BILLINGSYSTEM,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
