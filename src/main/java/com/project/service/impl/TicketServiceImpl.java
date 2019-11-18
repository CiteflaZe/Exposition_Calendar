package com.project.service.impl;

import com.project.dao.TicketDao;
import com.project.domain.ticket.Ticket;
import com.project.entity.ticket.TicketEntity;
import com.project.exception.InvalidEntityException;
import com.project.service.TicketService;
import com.project.service.mapper.TicketMapper;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
            throw new InvalidEntityException("Ticket is null");
        }
        final TicketEntity entity = mapper.mapTicketToTicketEntity(ticket);
        return ticketDao.save(entity);
    }

    @Override
    public List<Ticket> showByPaymentId(Long id) {
        final List<TicketEntity> entities = ticketDao.findByPaymentId(id);
        return mapTicketEntityListToTicketList(entities);
    }

    private List<Ticket> mapTicketEntityListToTicketList(List<TicketEntity> entities) {
        return entities.stream()
                .map(mapper::mapTicketEntityToTicket)
                .collect(Collectors.toList());
    }
}
