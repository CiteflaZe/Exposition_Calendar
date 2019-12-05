package com.project.service.impl;

import com.project.dao.TicketDao;
import com.project.domain.Ticket;
import com.project.entity.TicketEntity;
import com.project.exception.EntityNotFoundException;
import com.project.service.TicketService;
import com.project.service.mapper.TicketMapper;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

public class TicketServiceImpl implements TicketService {
    private static final Logger LOGGER = Logger.getLogger(TicketServiceImpl.class);

    private final TicketDao ticketDao;
    private final TicketMapper mapper;

    public TicketServiceImpl(TicketDao ticketDao, TicketMapper mapper) {
        this.ticketDao = ticketDao;
        this.mapper = mapper;
    }

    @Override
    public boolean add(Ticket ticket) {
        if (ticket == null) {
            LOGGER.warn("Ticket can't be null");
            throw new IllegalArgumentException("Ticket is null");
        }

        final TicketEntity entity = mapper.mapTicketToTicketEntity(ticket);
        return ticketDao.save(entity);
    }

    @Override
    public Ticket showOneByPaymentId(Long id) {
        final Optional<TicketEntity> ticketEntity = ticketDao.findFirstByPaymentId(id);
        return ticketEntity.map(mapper::mapTicketEntityToTicket).orElseThrow(() -> new EntityNotFoundException("No tickets found"));
    }

    @Override
    public List<Ticket> showAllByPaymentIdAndUserId(Long paymentId, Long userId) {
        final List<TicketEntity> tickets = ticketDao.findByPaymentIdAndUserId(paymentId, userId);
        return mapTicketEntityListToTicketList(tickets);
    }

    @Override
    public List<Ticket> showAllByUserId(Long id) {
        final List<TicketEntity> tickets = ticketDao.findAllByUserId(id);
        return mapTicketEntityListToTicketList(tickets);
    }

    private List<Ticket> mapTicketEntityListToTicketList(List<TicketEntity> entities) {
        return entities.isEmpty() ?
                emptyList() :
                entities.stream()
                        .map(mapper::mapTicketEntityToTicket)
                        .collect(Collectors.toList());
    }
}
