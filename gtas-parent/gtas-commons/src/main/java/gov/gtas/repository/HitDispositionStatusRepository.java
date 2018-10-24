/*
 * All GTAS code is Copyright 2016, The Department of Homeland Security (DHS), U.S. Customs and Border Protection (CBP).
 *
 * Please see LICENSE.txt for details.
 */
package gov.gtas.repository;

import gov.gtas.model.lookup.HitDispositionStatus;
import org.springframework.data.repository.CrudRepository;

import gov.gtas.model.lookup.DispositionStatus;

public interface HitDispositionStatusRepository extends CrudRepository<HitDispositionStatus, Long>{
}
