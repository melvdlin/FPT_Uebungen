package org.somevand.fpt.teaching.libraryproject.persistence.sqlite;

import org.somevand.fpt.teaching.libraryproject.entities.*;
import org.somevand.fpt.teaching.libraryproject.mediaquery.AuthorName;
import org.sqlite.JDBC;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

public class SQLitePersistence implements
        org.somevand.fpt.teaching.libraryproject.mediacheckout.CheckoutPersistenceGateway,
        org.somevand.fpt.teaching.libraryproject.checkoutlisting.CheckoutPersistenceGateway,
        org.somevand.fpt.teaching.libraryproject.customermanagement.CustomerPersistenceGateway,
        org.somevand.fpt.teaching.libraryproject.checkoutlisting.CustomerPersistenceGateway,
        org.somevand.fpt.teaching.libraryproject.mediacheckout.CustomerPersistenceGateway,
        org.somevand.fpt.teaching.libraryproject.mediamanagement.InventoryPersistenceGateway,
        org.somevand.fpt.teaching.libraryproject.mediacheckout.InventoryPersistenceGateway,
        org.somevand.fpt.teaching.libraryproject.mediaquery.InventoryAccessGateway,
        org.somevand.fpt.teaching.libraryproject.mediaquery.InventoryQueryGateway {
    private static class Labels {
        private static final String MEDIA_TABLE = "Media";
        private static final String BOOK_TABLE = "Books";
        private static final String ARTICLE_TABLE = "Articles";
        private static final String VIDEO_TABLE = "Videos";
        private static final String AUTHOR_TABLE = "Authors";
        private static final String MEDIA_AUTHOR_TABLE = "MediaAuthors";
        private static final String MEDIA_INSTANCE_TABLE = "MediaInstances";
        private static final String PHYSICAL_INSTANCE_TABLE = "PhysicalInstances";
        private static final String DIGITAL_INSTANCE_TABLE = "DigitalInstances";
        private static final String CUSTOMER_TABLE = "Customers";
        private static final String CHECKOUT_TABLE = "Checkouts";
    }

    private Connection connection;

    public SQLitePersistence(Path dbFile) throws SQLException {
        connection = DriverManager.getConnection(JDBC.PREFIX + dbFile.toString());
    }

    @Override
    public Collection<PhysicalMediaInstance> getCheckedOutMediaInstancesByCustomer(Customer customer) {
        return null;
    }

    @Override
    public Optional<LocalDateTime> getCheckoutTime(PhysicalMediaInstance instance) {
        return Optional.empty();
    }

    @Override
    public int getNewUID() {
        return 0;
    }

    @Override
    public void addCustomer(Customer customer) {

    }

    @Override
    public void removeCustomer(Customer customer) {

    }

    @Override
    public Optional<Customer> getCustomerByUID(int UID) {
        return Optional.empty();
    }

    @Override
    public void checkout(Collection<PhysicalMediaInstance> instances, Customer customer) {

    }

    @Override
    public void checkin(Collection<PhysicalMediaInstance> instances) {

    }

    @Override
    public boolean isCheckedOut(PhysicalMediaInstance instance) {
        return false;
    }

    @Override
    public Optional<PhysicalMediaInstance> getInstanceByUID(int uid) {
        return Optional.empty();
    }

    @Override
    public int getNewMediaUid() {
        return 0;
    }

    @Override
    public int getNewAuthorUid() {
        return 0;
    }

    @Override
    public int getNewInstanceUid() {
        return 0;
    }

    @Override
    public Optional<Media> getMediaByUid(int uid) {
        return Optional.empty();
    }

    @Override
    public Optional<Author> getAuthorByUid(int uid) {
        return Optional.empty();
    }

    @Override
    public Optional<MediaInstance> getInstanceByUid(int uid) {
        return Optional.empty();
    }

    @Override
    public void addMedia(Media media) {

    }

    @Override
    public void addAuthor(Author author) {

    }

    @Override
    public void addDigitalInstance(DigitalMediaInstance instance) {

    }

    @Override
    public void addPhysicalInstance(PhysicalMediaInstance instance) {

    }

    @Override
    public void removeMedia(Media media) {

    }

    @Override
    public void removeAuthor(Author author) {

    }

    @Override
    public void removeInstance(MediaInstance instance) {

    }

    @Override
    public Collection<MediaInstance> getInstances(Media media) {
        return null;
    }

    @Override
    public Collection<Media> queryMediaByUids(Collection<Integer> uids) {
        return null;
    }

    @Override
    public Collection<Media> queryMediaByTitles(Collection<String> titles, boolean conjunctive) {
        return null;
    }

    @Override
    public Collection<Media> queryMediaByAuthors(Collection<Author> authors, boolean conjunctive) {
        return null;
    }

    @Override
    public Collection<Author> queryAuthorsByUids(Collection<Integer> uids) {
        return null;
    }

    @Override
    public Collection<Author> queryAuthorsByNames(Collection<AuthorName> names) {
        return null;
    }
}
