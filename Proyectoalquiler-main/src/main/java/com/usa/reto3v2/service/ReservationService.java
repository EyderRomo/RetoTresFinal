package com.usa.reto3v2.service;

import com.usa.reto3v2.entities.Reservation;
import com.usa.reto3v2.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll() {
        return reservationRepository.getAll();
    }

    public Optional<Reservation> getReservation(int id) {
        return reservationRepository.getReservation(id);
    }

    public Reservation save(Reservation reservacion) {
        if (reservacion.getIdReservation() == null) {
            return reservationRepository.save(reservacion);
        } else {
            Optional<Reservation> a = reservationRepository.getReservation(reservacion.getIdReservation());
            if (a.isPresent()) {
                return reservacion;
            } else {
                return reservationRepository.save(reservacion);
            }
        }
    }

    public Reservation update(Reservation reservacion) {
        if (reservacion.getIdReservation() != null) {
            Optional<Reservation> rs = reservationRepository.getReservation(reservacion.getIdReservation());
            if (rs.isPresent()) {
                if (reservacion.getStartDate() != null) {
                    rs.get().setStartDate(reservacion.getStartDate());
                }
                if (reservacion.getDevolutionDate() != null) {
                    rs.get().setDevolutionDate(reservacion.getDevolutionDate());
                }
                if (reservacion.getStatus() != null) {
                    rs.get().setStatus(reservacion.getStatus());
                }
                if (reservacion.getScore() != null) {
                    rs.get().setScore(reservacion.getScore());
                }
                if (reservacion.getMotorbike() != null) {
                    rs.get().setMotorbike(reservacion.getMotorbike());
                }
                if (reservacion.getClient() != null) {
                    rs.get().setClient(reservacion.getClient());
                }


                reservationRepository.save(rs.get());
                return rs.get();

            } else {
                return reservacion;
            }
        } else {
            return reservacion;
        }
    }

    public boolean delete(int id) {
        boolean marca = false;
        Optional<Reservation> a = reservationRepository.getReservation(id);
        if (a.isPresent()) {
            reservationRepository.delete(a.get());
            marca = true;
        }
        return marca;

    }
}
