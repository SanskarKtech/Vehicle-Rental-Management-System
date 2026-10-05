package vehicleRentalSystem;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/*
 * ================================================================
 * VEHICLE RENTAL MANAGEMENT SYSTEM
 * ================================================================
 *
 * Java Swing Based Application
 *
 * Modules:
 * 1. Login
 * 2. Dashboard
 * 3. Vehicle Management
 * 4. Customer Management
 * 5. Vehicle Rental
 * 6. Vehicle Return
 * 7. Rental History
 * 8. Reports
 * 9. Invoice Generation
 *
 * Login:
 * Username : admin
 * Password : admin123
 *
 * ================================================================
 */

public class VehicleRentalSystem extends JFrame {

    // ================================================================
    // COLOR PALETTE
    // ================================================================

    static final Color DARK_BG = new Color(15, 23, 42);
    static final Color SIDEBAR = new Color(17, 24, 39);
    static final Color SIDEBAR_2 = new Color(30, 41, 59);

    static final Color TOPBAR = new Color(255, 255, 255);
    static final Color MAIN_BG = new Color(241, 245, 249);
    static final Color CARD = new Color(255, 255, 255);

    static final Color PRIMARY = new Color(37, 99, 235);
    static final Color PRIMARY_DARK = new Color(29, 78, 216);

    static final Color SUCCESS = new Color(16, 185, 129);
    static final Color SUCCESS_DARK = new Color(5, 150, 105);

    static final Color WARNING = new Color(245, 158, 11);
    static final Color DANGER = new Color(239, 68, 68);

    static final Color TEXT = new Color(15, 23, 42);
    static final Color TEXT_SECONDARY = new Color(71, 85, 105);
    static final Color TEXT_LIGHT = new Color(148, 163, 184);

    static final Color BORDER = new Color(226, 232, 240);

    // ================================================================
    // DATA
    // ================================================================

    static ArrayList<Vehicle> vehicles = new ArrayList<>();
    static ArrayList<Customer> customers = new ArrayList<>();
    static ArrayList<Rental> rentals = new ArrayList<>();

    static int vehicleCounter = 1001;
    static int customerCounter = 501;
    static int rentalCounter = 1;

    // ================================================================
    // MAIN UI
    // ================================================================

    JPanel contentPanel;
    CardLayout cardLayout;

    JLabel dashboardVehicles;
    JLabel dashboardCustomers;
    JLabel dashboardRentals;
    JLabel dashboardRevenue;

    // ================================================================
    // CONSTRUCTOR
    // ================================================================

    public VehicleRentalSystem() {

        initializeSampleData();

        setTitle("Vehicle Rental Management System");
        setSize(1280, 760);
        setMinimumSize(new Dimension(1100, 650));

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buildInterface();
    }

    // ================================================================
    // BUILD MAIN INTERFACE
    // ================================================================

    private void buildInterface() {

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(MAIN_BG);

        root.add(createSidebar(), BorderLayout.WEST);

        JPanel rightSide = new JPanel(new BorderLayout());
        rightSide.setBackground(MAIN_BG);

        rightSide.add(createTopBar(), BorderLayout.NORTH);

        cardLayout = new CardLayout();

        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(MAIN_BG);

        contentPanel.add(createDashboard(), "Dashboard");
        contentPanel.add(createVehiclePanel(), "Vehicles");
        contentPanel.add(createCustomerPanel(), "Customers");
        contentPanel.add(createRentalPanel(), "Rental");
        contentPanel.add(createReturnPanel(), "Return");
        contentPanel.add(createHistoryPanel(), "History");
        contentPanel.add(createReportsPanel(), "Reports");

        rightSide.add(contentPanel, BorderLayout.CENTER);

        root.add(rightSide, BorderLayout.CENTER);

        setContentPane(root);
    }

    // ================================================================
    // SIDEBAR
    // ================================================================

    private JPanel createSidebar() {

        JPanel sidebar = new JPanel(new BorderLayout());

        sidebar.setBackground(SIDEBAR);
        sidebar.setPreferredSize(new Dimension(245, 0));

        // Logo
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(SIDEBAR);

        logoPanel.setLayout(
                new BoxLayout(
                        logoPanel,
                        BoxLayout.Y_AXIS
                )
        );

        logoPanel.setBorder(
                new EmptyBorder(
                        30,
                        20,
                        25,
                        20
                )
        );

        JLabel logo = new JLabel("VRMS");

        logo.setForeground(Color.WHITE);
        logo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        32
                )
        );

        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle =
                new JLabel("VEHICLE RENTAL MANAGEMENT");

        subtitle.setForeground(
                new Color(148, 163, 184)
        );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        9
                )
        );

        subtitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        logoPanel.add(logo);

        logoPanel.add(
                Box.createVerticalStrut(6)
        );

        logoPanel.add(subtitle);

        sidebar.add(
                logoPanel,
                BorderLayout.NORTH
        );

        // Menu
        JPanel menu = new JPanel();

        menu.setBackground(SIDEBAR);

        menu.setLayout(
                new BoxLayout(
                        menu,
                        BoxLayout.Y_AXIS
                )
        );

        menu.setBorder(
                new EmptyBorder(
                        10,
                        12,
                        10,
                        12
                )
        );

        JLabel menuTitle =
                new JLabel("MAIN MENU");

        menuTitle.setForeground(
                TEXT_LIGHT
        );

        menuTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        10
                )
        );

        menuTitle.setBorder(
                new EmptyBorder(
                        5,
                        15,
                        12,
                        0
                )
        );

        menu.add(menuTitle);

        menu.add(
                createSidebarButton(
                        "Dashboard",
                        "Dashboard"
                )
        );

        menu.add(
                createSidebarButton(
                        "Vehicle Management",
                        "Vehicles"
                )
        );

        menu.add(
                createSidebarButton(
                        "Customer Management",
                        "Customers"
                )
        );

        menu.add(
                createSidebarButton(
                        "New Rental",
                        "Rental"
                )
        );

        menu.add(
                createSidebarButton(
                        "Return Vehicle",
                        "Return"
                )
        );

        menu.add(
                createSidebarButton(
                        "Rental History",
                        "History"
                )
        );

        menu.add(
                createSidebarButton(
                        "Reports & Analytics",
                        "Reports"
                )
        );

        sidebar.add(
                menu,
                BorderLayout.CENTER
        );

        // Bottom
        JPanel bottom =
                new JPanel(
                        new BorderLayout()
                );

        bottom.setBackground(
                SIDEBAR
        );

        bottom.setBorder(
                new EmptyBorder(
                        10,
                        15,
                        25,
                        15
                )
        );

        JButton logout =
                createSidebarButton(
                        "Logout",
                        ""
                );

        logout.addActionListener(
                e -> logout()
        );

        bottom.add(
                logout,
                BorderLayout.CENTER
        );

        sidebar.add(
                bottom,
                BorderLayout.SOUTH
        );

        return sidebar;
    }

    // ================================================================
    // SIDEBAR BUTTON
    // ================================================================

    private JButton createSidebarButton(
            String text,
            String card
    ) {

        JButton button =
                new JButton(text);

        button.setPreferredSize(
                new Dimension(
                        215,
                        46
                )
        );

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        46
                )
        );

        button.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setBackground(
                SIDEBAR
        );

        button.setForeground(
                new Color(
                        203,
                        213,
                        225
                )
        );

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        button.setBorder(
                new EmptyBorder(
                        0,
                        18,
                        0,
                        10
                )
        );

        button.setFocusPainted(false);
        button.setOpaque(true);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.addMouseListener(
                new MouseAdapter() {

                    public void mouseEntered(
                            MouseEvent e
                    ) {

                        button.setBackground(
                                SIDEBAR_2
                        );

                        button.setForeground(
                                Color.WHITE
                        );
                    }

                    public void mouseExited(
                            MouseEvent e
                    ) {

                        button.setBackground(
                                SIDEBAR
                        );

                        button.setForeground(
                                new Color(
                                        203,
                                        213,
                                        225
                                )
                        );
                    }
                }
        );

        if (!card.isEmpty()) {

            button.addActionListener(
                    e -> {

                        cardLayout.show(
                                contentPanel,
                                card
                        );

                        if (card.equals(
                                "Dashboard"
                        )) {

                            refreshDashboard();
                        }
                    }
            );
        }

        return button;
    }

    // ================================================================
    // TOP BAR
    // ================================================================

    private JPanel createTopBar() {

        JPanel top =
                new JPanel(
                        new BorderLayout()
                );

        top.setBackground(
                TOPBAR
        );

        top.setPreferredSize(
                new Dimension(
                        0,
                        75
                )
        );

        top.setBorder(
                new MatteBorder(
                        0,
                        0,
                        1,
                        0,
                        BORDER
                )
        );

        JPanel left =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                25,
                                18
                        )
                );

        left.setOpaque(false);

        JLabel title =
                new JLabel(
                        "Vehicle Rental Management System"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );

        title.setForeground(
                TEXT
        );

        left.add(title);

        JPanel right =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                20,
                                15
                        )
                );

        right.setOpaque(false);

        JLabel admin =
                new JLabel(
                        "Administrator"
                );

        admin.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        admin.setForeground(
                TEXT
        );

        JLabel role =
                new JLabel(
                        "Admin"
                );

        role.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        11
                )
        );

        role.setForeground(
                TEXT_SECONDARY
        );

        JPanel adminInfo =
                new JPanel();

        adminInfo.setOpaque(false);

        adminInfo.setLayout(
                new BoxLayout(
                        adminInfo,
                        BoxLayout.Y_AXIS
                )
        );

        adminInfo.add(admin);
        adminInfo.add(role);

        right.add(adminInfo);

        top.add(
                left,
                BorderLayout.WEST
        );

        top.add(
                right,
                BorderLayout.EAST
        );

        return top;
    }

    // ================================================================
    // DASHBOARD
    // ================================================================

    private JPanel createDashboard() {

        JPanel panel =
                createBasePanel();

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setOpaque(false);

        JLabel title =
                new JLabel(
                        "Dashboard"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        title.setForeground(
                TEXT
        );

        JLabel date =
                new JLabel(
                        new SimpleDateFormat(
                                "EEEE, dd MMMM yyyy"
                        ).format(
                                new Date()
                        )
                );

        date.setForeground(
                TEXT_SECONDARY
        );

        date.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        header.add(
                title,
                BorderLayout.WEST
        );

        header.add(
                date,
                BorderLayout.EAST
        );

        panel.add(
                header,
                BorderLayout.NORTH
        );

        JPanel body =
                new JPanel(
                        new BorderLayout(
                                15,
                                15
                        )
                );

        body.setOpaque(false);

        JPanel stats =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                15,
                                15
                        )
                );

        stats.setOpaque(false);

        dashboardVehicles =
                new JLabel("0");

        dashboardCustomers =
                new JLabel("0");

        dashboardRentals =
                new JLabel("0");

        dashboardRevenue =
                new JLabel("₹0");

        stats.add(
                createStatCard(
                        "TOTAL VEHICLES",
                        dashboardVehicles,
                        PRIMARY
                )
        );

        stats.add(
                createStatCard(
                        "CUSTOMERS",
                        dashboardCustomers,
                        SUCCESS
                )
        );

        stats.add(
                createStatCard(
                        "ACTIVE RENTALS",
                        dashboardRentals,
                        WARNING
                )
        );

        stats.add(
                createStatCard(
                        "REVENUE",
                        dashboardRevenue,
                        DANGER
                )
        );

        body.add(
                stats,
                BorderLayout.NORTH
        );

        JPanel lower =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                15,
                                15
                        )
                );

        lower.setOpaque(false);

        lower.add(
                createQuickActions()
        );

        lower.add(
                createSystemInfo()
        );

        body.add(
                lower,
                BorderLayout.CENTER
        );

        panel.add(
                body,
                BorderLayout.CENTER
        );

        refreshDashboard();

        return panel;
    }

    // ================================================================
    // BASE PANEL
    // ================================================================

    private JPanel createBasePanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                15,
                                15
                        )
                );

        panel.setBackground(
                MAIN_BG
        );

        panel.setBorder(
                new EmptyBorder(
                        25,
                        25,
                        25,
                        25
                )
        );

        return panel;
    }

    // ================================================================
    // STAT CARD
    // ================================================================

    private JPanel createStatCard(
            String title,
            JLabel value,
            Color accent
    ) {

        JPanel card =
                new JPanel(
                        new BorderLayout(
                                10,
                                5
                        )
                );

        card.setBackground(
                CARD
        );

        card.setBorder(
                new CompoundBorder(
                        new LineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                18,
                                20,
                                18,
                                20
                        )
                )
        );

        JLabel t =
                new JLabel(title);

        t.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        11
                )
        );

        t.setForeground(
                TEXT_SECONDARY
        );

        value.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        value.setForeground(
                accent
        );

        card.add(
                t,
                BorderLayout.NORTH
        );

        card.add(
                value,
                BorderLayout.CENTER
        );

        return card;
    }

    // ================================================================
    // QUICK ACTIONS
    // ================================================================

    private JPanel createQuickActions() {

        JPanel panel =
                createWhiteCard();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel title =
                createSectionTitle(
                        "Quick Actions"
                );

        panel.add(title);

        panel.add(
                Box.createVerticalStrut(20)
        );

        JButton vehicle =
                createPrimaryButton(
                        "+ Add New Vehicle"
                );

        JButton customer =
                createSuccessButton(
                        "+ Register Customer"
                );

        JButton rental =
                createPrimaryButton(
                        "Create New Rental"
                );

        vehicle.addActionListener(
                e -> cardLayout.show(
                        contentPanel,
                        "Vehicles"
                )
        );

        customer.addActionListener(
                e -> cardLayout.show(
                        contentPanel,
                        "Customers"
                )
        );

        rental.addActionListener(
                e -> cardLayout.show(
                        contentPanel,
                        "Rental"
                )
        );

        panel.add(vehicle);
        panel.add(
                Box.createVerticalStrut(10)
        );

        panel.add(customer);
        panel.add(
                Box.createVerticalStrut(10)
        );

        panel.add(rental);

        return panel;
    }

    // ================================================================
    // SYSTEM INFORMATION
    // ================================================================

    private JPanel createSystemInfo() {

        JPanel panel =
                createWhiteCard();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.add(
                createSectionTitle(
                        "System Information"
                )
        );

        panel.add(
                Box.createVerticalStrut(20)
        );

        panel.add(
                createInfoRow(
                        "System Status",
                        "ONLINE",
                        SUCCESS
                )
        );

        panel.add(
                createInfoRow(
                        "Storage",
                        "In Memory",
                        PRIMARY
                )
        );

        panel.add(
                createInfoRow(
                        "Application",
                        "VRMS",
                        TEXT
                )
        );

        panel.add(
                createInfoRow(
                        "Version",
                        "1.0",
                        TEXT
                )
        );

        panel.add(
                createInfoRow(
                        "User",
                        "Administrator",
                        TEXT
                )
        );

        return panel;
    }

    // ================================================================
    // INFO ROW
    // ================================================================

    private JPanel createInfoRow(
            String title,
            String value,
            Color color
    ) {

        JPanel row =
                new JPanel(
                        new BorderLayout()
                );

        row.setOpaque(false);

        row.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        40
                )
        );

        JLabel t =
                new JLabel(title);

        t.setForeground(
                TEXT_SECONDARY
        );

        JLabel v =
                new JLabel(value);

        v.setForeground(
                color
        );

        v.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        row.add(
                t,
                BorderLayout.WEST
        );

        row.add(
                v,
                BorderLayout.EAST
        );

        return row;
    }

    // ================================================================
    // VEHICLE MANAGEMENT
    // ================================================================

    private JPanel createVehiclePanel() {

        JPanel panel =
                createBasePanel();

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setOpaque(false);

        JLabel title =
                new JLabel(
                        "Vehicle Management"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        26
                )
        );

        title.setForeground(
                TEXT
        );

        JButton add =
                createPrimaryButton(
                        "+ Add Vehicle"
                );

        add.addActionListener(
                e -> showVehicleDialog(null)
        );

        header.add(
                title,
                BorderLayout.WEST
        );

        header.add(
                add,
                BorderLayout.EAST
        );

        panel.add(
                header,
                BorderLayout.NORTH
        );

        JPanel center =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        center.setOpaque(false);

        JPanel search =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                10
                        )
                );

        search.setBackground(
                CARD
        );

        search.setBorder(
                new LineBorder(
                        BORDER
                )
        );

        JTextField searchField =
                new JTextField(25);

        styleTextField(searchField);

        JButton searchButton =
                createPrimaryButton(
                        "Search"
                );

        JButton refresh =
                createSecondaryButton(
                        "Refresh"
                );

        search.add(
                new JLabel(
                        "Search Vehicles:"
                )
        );

        search.add(
                searchField
        );

        search.add(
                searchButton
        );

        search.add(
                refresh
        );

        center.add(
                search,
                BorderLayout.NORTH
        );

        String[] columns = {
                "ID",
                "Registration",
                "Vehicle",
                "Category",
                "Fuel",
                "Rate / Day",
                "Status"
        };

        DefaultTableModel model =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {
                        return false;
                    }
                };

        JTable table =
                createTable(model);

        center.add(
                createTableContainer(
                        table
                ),
                BorderLayout.CENTER
        );

        JPanel buttons =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        buttons.setOpaque(false);

        JButton edit =
                createPrimaryButton(
                        "Edit Vehicle"
                );

        JButton delete =
                createDangerButton(
                        "Delete Vehicle"
                );

        buttons.add(edit);
        buttons.add(delete);

        center.add(
                buttons,
                BorderLayout.SOUTH
        );

        panel.add(
                center,
                BorderLayout.CENTER
        );

        Runnable load = () -> {

            model.setRowCount(0);

            String keyword =
                    searchField
                            .getText()
                            .trim()
                            .toLowerCase();

            for (Vehicle v : vehicles) {

                String combined =
                        v.id + " " +
                                v.registration + " " +
                                v.brand + " " +
                                v.model + " " +
                                v.category;

                if (
                        keyword.isEmpty()
                                ||
                                combined
                                        .toLowerCase()
                                        .contains(
                                                keyword
                                        )
                ) {

                    model.addRow(
                            new Object[]{
                                    v.id,
                                    v.registration,
                                    v.brand + " " +
                                            v.model,
                                    v.category,
                                    v.fuel,
                                    String.format(
                                            "₹%.2f",
                                            v.ratePerDay
                                    ),
                                    v.available
                                            ? "AVAILABLE"
                                            : "RENTED"
                            }
                    );
                }
            }
        };

        searchButton.addActionListener(
                e -> load.run()
        );

        refresh.addActionListener(
                e -> {

                    searchField.setText("");

                    load.run();
                }
        );

        edit.addActionListener(
                e -> {

                    int row =
                            table.getSelectedRow();

                    if (row == -1) {

                        showMessage(
                                "Please select a vehicle."
                        );

                        return;
                    }

                    int id =
                            Integer.parseInt(
                                    table.getValueAt(
                                            row,
                                            0
                                    ).toString()
                            );

                    Vehicle v =
                            findVehicle(id);

                    if (v != null) {

                        showVehicleDialog(v);

                        load.run();
                    }
                }
        );

        delete.addActionListener(
                e -> {

                    int row =
                            table.getSelectedRow();

                    if (row == -1) {

                        showMessage(
                                "Please select a vehicle."
                        );

                        return;
                    }

                    int id =
                            Integer.parseInt(
                                    table.getValueAt(
                                            row,
                                            0
                                    ).toString()
                            );

                    Vehicle v =
                            findVehicle(id);

                    if (
                            v != null
                                    &&
                                    !v.available
                    ) {

                        showMessage(
                                "This vehicle is currently rented."
                        );

                        return;
                    }

                    int result =
                            JOptionPane.showConfirmDialog(
                                    this,
                                    "Delete selected vehicle?",
                                    "Confirm Delete",
                                    JOptionPane.YES_NO_OPTION
                            );

                    if (
                            result ==
                                    JOptionPane.YES_OPTION
                    ) {

                        vehicles.remove(v);

                        load.run();

                        refreshDashboard();
                    }
                }
        );

        load.run();

        return panel;
    }

    // ================================================================
    // VEHICLE DIALOG
    // ================================================================

    private void showVehicleDialog(
            Vehicle vehicle
    ) {

        JDialog dialog =
                new JDialog(
                        this,
                        vehicle == null
                                ? "Add Vehicle"
                                : "Edit Vehicle",
                        true
                );

        dialog.setSize(
                520,
                520
        );

        dialog.setLocationRelativeTo(
                this
        );

        JPanel panel =
                new JPanel(
                        new GridBagLayout()
                );

        panel.setBackground(
                CARD
        );

        panel.setBorder(
                new EmptyBorder(
                        25,
                        30,
                        25,
                        30
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        9,
                        9,
                        9,
                        9
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        JTextField registration =
                new JTextField();

        JTextField brand =
                new JTextField();

        JTextField model =
                new JTextField();

        JComboBox<String> category =
                new JComboBox<>(
                        new String[]{
                                "Hatchback",
                                "Sedan",
                                "SUV",
                                "Luxury",
                                "Sports",
                                "Bike",
                                "Electric"
                        }
                );

        JComboBox<String> fuel =
                new JComboBox<>(
                        new String[]{
                                "Petrol",
                                "Diesel",
                                "CNG",
                                "Electric",
                                "Hybrid"
                        }
                );

        JTextField rate =
                new JTextField();

        styleTextField(registration);
        styleTextField(brand);
        styleTextField(model);
        styleComboBox(category);
        styleComboBox(fuel);
        styleTextField(rate);

        if (vehicle != null) {

            registration.setText(
                    vehicle.registration
            );

            brand.setText(
                    vehicle.brand
            );

            model.setText(
                    vehicle.model
            );

            category.setSelectedItem(
                    vehicle.category
            );

            fuel.setSelectedItem(
                    vehicle.fuel
            );

            rate.setText(
                    String.valueOf(
                            vehicle.ratePerDay
                    )
            );
        }

        addFormField(
                panel,
                gbc,
                0,
                "Registration Number",
                registration
        );

        addFormField(
                panel,
                gbc,
                1,
                "Brand",
                brand
        );

        addFormField(
                panel,
                gbc,
                2,
                "Model",
                model
        );

        addFormField(
                panel,
                gbc,
                3,
                "Category",
                category
        );

        addFormField(
                panel,
                gbc,
                4,
                "Fuel Type",
                fuel
        );

        addFormField(
                panel,
                gbc,
                5,
                "Rent Per Day",
                rate
        );

        JButton save =
                createPrimaryButton(
                        vehicle == null
                                ? "Add Vehicle"
                                : "Update Vehicle"
                );

        gbc.gridx = 1;
        gbc.gridy = 7;

        panel.add(
                save,
                gbc
        );

        save.addActionListener(
                e -> {

                    if (
                            registration
                                    .getText()
                                    .trim()
                                    .isEmpty()
                                    ||
                                    brand
                                            .getText()
                                            .trim()
                                            .isEmpty()
                                    ||
                                    model
                                            .getText()
                                            .trim()
                                            .isEmpty()
                                    ||
                                    rate
                                            .getText()
                                            .trim()
                                            .isEmpty()
                    ) {

                        showMessage(
                                "Please fill all fields."
                        );

                        return;
                    }

                    double rentalRate;

                    try {

                        rentalRate =
                                Double.parseDouble(
                                        rate.getText()
                                                .trim()
                                );

                        if (
                                rentalRate <= 0
                        ) {
                            throw new NumberFormatException();
                        }

                    } catch (
                            NumberFormatException ex
                    ) {

                        showMessage(
                                "Enter a valid rental rate."
                        );

                        return;
                    }

                    if (vehicle == null) {

                        Vehicle v =
                                new Vehicle(
                                        vehicleCounter++,
                                        registration
                                                .getText()
                                                .trim()
                                                .toUpperCase(),
                                        brand
                                                .getText()
                                                .trim(),
                                        model
                                                .getText()
                                                .trim(),
                                        category
                                                .getSelectedItem()
                                                .toString(),
                                        fuel
                                                .getSelectedItem()
                                                .toString(),
                                        rentalRate
                                );

                        vehicles.add(v);

                        showMessage(
                                "Vehicle added successfully."
                        );

                    } else {

                        vehicle.registration =
                                registration
                                        .getText()
                                        .trim()
                                        .toUpperCase();

                        vehicle.brand =
                                brand
                                        .getText()
                                        .trim();

                        vehicle.model =
                                model
                                        .getText()
                                        .trim();

                        vehicle.category =
                                category
                                        .getSelectedItem()
                                        .toString();

                        vehicle.fuel =
                                fuel
                                        .getSelectedItem()
                                        .toString();

                        vehicle.ratePerDay =
                                rentalRate;

                        showMessage(
                                "Vehicle updated successfully."
                        );
                    }

                    dialog.dispose();
                }
        );

        dialog.add(panel);

        dialog.setVisible(true);
    }

    // ================================================================
    // CUSTOMER MANAGEMENT
    // ================================================================

    private JPanel createCustomerPanel() {

        JPanel panel =
                createBasePanel();

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setOpaque(false);

        JLabel title =
                new JLabel(
                        "Customer Management"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        26
                )
        );

        title.setForeground(
                TEXT
        );

        JButton add =
                createSuccessButton(
                        "+ Register Customer"
                );

        header.add(
                title,
                BorderLayout.WEST
        );

        header.add(
                add,
                BorderLayout.EAST
        );

        panel.add(
                header,
                BorderLayout.NORTH
        );

        JPanel center =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        center.setOpaque(false);

        JPanel search =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT
                        )
                );

        search.setBackground(
                CARD
        );

        search.setBorder(
                new LineBorder(
                        BORDER
                )
        );

        JTextField field =
                new JTextField(25);

        styleTextField(field);

        JButton searchButton =
                createPrimaryButton(
                        "Search"
                );

        search.add(
                new JLabel(
                        "Search Customer:"
                )
        );

        search.add(field);

        search.add(searchButton);

        center.add(
                search,
                BorderLayout.NORTH
        );

        String[] columns = {
                "Customer ID",
                "Name",
                "Phone",
                "Email",
                "License",
                "Address"
        };

        DefaultTableModel model =
                new DefaultTableModel(
                        columns,
                        0
                );

        JTable table =
                createTable(model);

        center.add(
                createTableContainer(table),
                BorderLayout.CENTER
        );

        JButton delete =
                createDangerButton(
                        "Delete Customer"
                );

        JPanel bottom =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        bottom.setOpaque(false);

        bottom.add(delete);

        center.add(
                bottom,
                BorderLayout.SOUTH
        );

        panel.add(
                center,
                BorderLayout.CENTER
        );

        Runnable load = () -> {

            model.setRowCount(0);

            String keyword =
                    field
                            .getText()
                            .trim()
                            .toLowerCase();

            for (Customer c :
                    customers) {

                String combined =
                        c.id + " " +
                                c.name + " " +
                                c.phone + " " +
                                c.email + " " +
                                c.license;

                if (
                        keyword.isEmpty()
                                ||
                                combined
                                        .toLowerCase()
                                        .contains(
                                                keyword
                                        )
                ) {

                    model.addRow(
                            new Object[]{
                                    c.id,
                                    c.name,
                                    c.phone,
                                    c.email,
                                    c.license,
                                    c.address
                            }
                    );
                }
            }
        };

        add.addActionListener(
                e -> {

                    showCustomerDialog();

                    load.run();

                    refreshDashboard();
                }
        );

        searchButton.addActionListener(
                e -> load.run()
        );

        delete.addActionListener(
                e -> {

                    int row =
                            table.getSelectedRow();

                    if (row == -1) {

                        showMessage(
                                "Select a customer."
                        );

                        return;
                    }

                    int id =
                            Integer.parseInt(
                                    table.getValueAt(
                                            row,
                                            0
                                    ).toString()
                            );

                    for (Rental r :
                            rentals) {

                        if (
                                !r.returned
                                        &&
                                        r.customerId == id
                        ) {

                            showMessage(
                                    "Customer has an active rental."
                            );

                            return;
                        }
                    }

                    Customer c =
                            findCustomer(id);

                    int result =
                            JOptionPane.showConfirmDialog(
                                    this,
                                    "Delete selected customer?",
                                    "Confirm Delete",
                                    JOptionPane.YES_NO_OPTION
                            );

                    if (
                            result ==
                                    JOptionPane.YES_OPTION
                    ) {

                        customers.remove(c);

                        load.run();

                        refreshDashboard();
                    }
                }
        );

        load.run();

        return panel;
    }

    // ================================================================
    // CUSTOMER DIALOG
    // ================================================================

    private void showCustomerDialog() {

        JDialog dialog =
                new JDialog(
                        this,
                        "Register Customer",
                        true
                );

        dialog.setSize(
                520,
                520
        );

        dialog.setLocationRelativeTo(
                this
        );

        JPanel panel =
                new JPanel(
                        new GridBagLayout()
                );

        panel.setBackground(
                CARD
        );

        panel.setBorder(
                new EmptyBorder(
                        25,
                        30,
                        25,
                        30
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        9,
                        9,
                        9,
                        9
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        JTextField name =
                new JTextField();

        JTextField phone =
                new JTextField();

        JTextField email =
                new JTextField();

        JTextField license =
                new JTextField();

        JTextField address =
                new JTextField();

        styleTextField(name);
        styleTextField(phone);
        styleTextField(email);
        styleTextField(license);
        styleTextField(address);

        addFormField(
                panel,
                gbc,
                0,
                "Full Name",
                name
        );

        addFormField(
                panel,
                gbc,
                1,
                "Phone Number",
                phone
        );

        addFormField(
                panel,
                gbc,
                2,
                "Email",
                email
        );

        addFormField(
                panel,
                gbc,
                3,
                "Driving License",
                license
        );

        addFormField(
                panel,
                gbc,
                4,
                "Address",
                address
        );

        JButton save =
                createSuccessButton(
                        "Register Customer"
                );

        gbc.gridx = 1;
        gbc.gridy = 6;

        panel.add(
                save,
                gbc
        );

        save.addActionListener(
                e -> {

                    String n =
                            name
                                    .getText()
                                    .trim();

                    String p =
                            phone
                                    .getText()
                                    .trim();

                    String em =
                            email
                                    .getText()
                                    .trim();

                    String l =
                            license
                                    .getText()
                                    .trim();

                    String a =
                            address
                                    .getText()
                                    .trim();

                    if (
                            n.isEmpty()
                                    ||
                                    p.isEmpty()
                                    ||
                                    em.isEmpty()
                                    ||
                                    l.isEmpty()
                                    ||
                                    a.isEmpty()
                    ) {

                        showMessage(
                                "Please fill all fields."
                        );

                        return;
                    }

                    if (
                            !p.matches(
                                    "\\d{10}"
                            )
                    ) {

                        showMessage(
                                "Phone number must contain exactly 10 digits."
                        );

                        return;
                    }

                    if (
                            !em.contains("@")
                    ) {

                        showMessage(
                                "Enter a valid email."
                        );

                        return;
                    }

                    customers.add(
                            new Customer(
                                    customerCounter++,
                                    n,
                                    p,
                                    em,
                                    l,
                                    a
                            )
                    );

                    showMessage(
                            "Customer registered successfully."
                    );

                    dialog.dispose();
                }
        );

        dialog.add(panel);

        dialog.setVisible(true);
    }

    // ================================================================
    // RENTAL PANEL
    // ================================================================

    private JPanel createRentalPanel() {

        JPanel panel =
                createBasePanel();

        JLabel title =
                new JLabel(
                        "Create New Rental"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        26
                )
        );

        title.setForeground(
                TEXT
        );

        panel.add(
                title,
                BorderLayout.NORTH
        );

        JPanel form =
                new JPanel(
                        new GridBagLayout()
                );

        form.setBackground(
                CARD
        );

        form.setBorder(
                new CompoundBorder(
                        new LineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                30,
                                60,
                                30,
                                60
                        )
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        12,
                        12,
                        12,
                        12
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        JComboBox<String> customerBox =
                new JComboBox<>();

        JComboBox<String> vehicleBox =
                new JComboBox<>();

        JTextField days =
                new JTextField();

        JTextField rate =
                new JTextField();

        JTextField deposit =
                new JTextField();

        styleComboBox(customerBox);
        styleComboBox(vehicleBox);

        styleTextField(days);
        styleTextField(rate);
        styleTextField(deposit);

        rate.setEditable(false);
        deposit.setEditable(false);

        loadCustomerCombo(
                customerBox
        );

        loadVehicleCombo(
                vehicleBox
        );

        addFormField(
                form,
                gbc,
                0,
                "Customer",
                customerBox
        );

        addFormField(
                form,
                gbc,
                1,
                "Vehicle",
                vehicleBox
        );

        addFormField(
                form,
                gbc,
                2,
                "Rental Days",
                days
        );

        addFormField(
                form,
                gbc,
                3,
                "Rate Per Day",
                rate
        );

        addFormField(
                form,
                gbc,
                4,
                "Security Deposit",
                deposit
        );

        JButton calculate =
                createPrimaryButton(
                        "Calculate"
                );

        JButton rent =
                createSuccessButton(
                        "Confirm Rental"
                );

        JPanel buttons =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT
                        )
                );

        buttons.setOpaque(false);

        buttons.add(calculate);
        buttons.add(rent);

        gbc.gridx = 1;
        gbc.gridy = 6;

        form.add(
                buttons,
                gbc
        );

        JLabel total =
                new JLabel(
                        "Estimated Total: ₹0.00"
                );

        total.setForeground(
                PRIMARY
        );

        total.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        gbc.gridy = 7;

        form.add(
                total,
                gbc
        );

        vehicleBox.addActionListener(
                e -> {

                    if (
                            vehicleBox
                                    .getSelectedItem()
                                    == null
                    ) {

                        return;
                    }

                    Vehicle v =
                            findVehicleFromCombo(
                                    vehicleBox
                                            .getSelectedItem()
                                            .toString()
                            );

                    if (v != null) {

                        rate.setText(
                                String.format(
                                        "%.2f",
                                        v.ratePerDay
                                )
                        );

                        deposit.setText(
                                String.format(
                                        "%.2f",
                                        v.ratePerDay * 2
                                )
                        );
                    }
                }
        );

        calculate.addActionListener(
                e -> {

                    try {

                        int d =
                                Integer.parseInt(
                                        days
                                                .getText()
                                                .trim()
                                );

                        if (d <= 0) {

                            throw new Exception();
                        }

                        Vehicle v =
                                findVehicleFromCombo(
                                        vehicleBox
                                                .getSelectedItem()
                                                .toString()
                                );

                        if (v == null) {

                            showMessage(
                                    "Select a vehicle."
                            );

                            return;
                        }

                        double totalAmount =
                                v.ratePerDay * d
                                        +
                                        v.ratePerDay * 2;

                        total.setText(
                                String.format(
                                        "Estimated Total: ₹%.2f",
                                        totalAmount
                                )
                        );

                    } catch (
                            Exception ex
                    ) {

                        showMessage(
                                "Enter valid rental days."
                        );
                    }
                }
        );

        rent.addActionListener(
                e -> {

                    try {

                        int d =
                                Integer.parseInt(
                                        days
                                                .getText()
                                                .trim()
                                );

                        if (d <= 0) {

                            throw new Exception();
                        }

                        Customer c =
                                findCustomerFromCombo(
                                        customerBox
                                                .getSelectedItem()
                                                .toString()
                                );

                        Vehicle v =
                                findVehicleFromCombo(
                                        vehicleBox
                                                .getSelectedItem()
                                                .toString()
                                );

                        if (
                                c == null
                                        ||
                                        v == null
                        ) {

                            showMessage(
                                    "Select valid customer and vehicle."
                            );

                            return;
                        }

                        if (!v.available) {

                            showMessage(
                                    "Vehicle is already rented."
                            );

                            return;
                        }

                        double amount =
                                v.ratePerDay * d;

                        double security =
                                v.ratePerDay * 2;

                        Rental rental =
                                new Rental(
                                        rentalCounter++,
                                        c.id,
                                        v.id,
                                        d,
                                        amount,
                                        security
                                );

                        rentals.add(
                                rental
                        );

                        v.available =
                                false;

                        showMessage(
                                "Rental created successfully.\n\n"
                                        +
                                        "Rental ID: "
                                        +
                                        rental.id
                        );

                        refreshDashboard();

                        loadVehicleCombo(
                                vehicleBox
                        );

                        days.setText("");

                        cardLayout.show(
                                contentPanel,
                                "History"
                        );

                    } catch (
                            Exception ex
                    ) {

                        showMessage(
                                "Please enter valid rental information."
                        );
                    }
                }
        );

        panel.add(
                form,
                BorderLayout.CENTER
        );

        return panel;
    }

    // ================================================================
    // RETURN PANEL
    // ================================================================

    private JPanel createReturnPanel() {

        JPanel panel =
                createBasePanel();

        JLabel title =
                new JLabel(
                        "Return Vehicle"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        26
                )
        );

        title.setForeground(
                TEXT
        );

        panel.add(
                title,
                BorderLayout.NORTH
        );

        String[] columns = {
                "Rental ID",
                "Customer",
                "Vehicle",
                "Days",
                "Due Date",
                "Rental Amount",
                "Deposit",
                "Status"
        };

        DefaultTableModel model =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };

        JTable table =
                createTable(model);

        JPanel center =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        center.setOpaque(false);

        center.add(
                createTableContainer(table),
                BorderLayout.CENTER
        );

        JPanel bottom =
                new JPanel(
                        new BorderLayout()
                );

        bottom.setOpaque(false);

        JLabel hint = new JLabel(
                "Select an active rental below, then click Return to settle it."
        );

        hint.setForeground(TEXT_SECONDARY);
        hint.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonRow.setOpaque(false);

        JButton refresh =
                createSecondaryButton("Refresh");

        JButton returnButton =
                createSuccessButton(
                        "Return Selected Vehicle"
                );

        buttonRow.add(refresh);
        buttonRow.add(returnButton);

        bottom.add(hint, BorderLayout.WEST);
        bottom.add(buttonRow, BorderLayout.EAST);

        center.add(
                bottom,
                BorderLayout.SOUTH
        );

        panel.add(
                center,
                BorderLayout.CENTER
        );

        Runnable load = () -> {

            model.setRowCount(0);

            for (Rental r :
                    rentals) {

                if (!r.returned) {

                    Customer c =
                            findCustomer(
                                    r.customerId
                            );

                    Vehicle v =
                            findVehicle(
                                    r.vehicleId
                            );

                    int autoLate = r.getAutoLateDays();

                    String status = autoLate > 0
                            ? autoLate + " day(s) OVERDUE"
                            : "ACTIVE";

                    model.addRow(
                            new Object[]{
                                    r.id,
                                    c != null
                                            ? c.name
                                            : "Unknown",
                                    v != null
                                            ? v.brand
                                            + " "
                                            + v.model
                                            : "Unknown",
                                    r.days,
                                    formatDate(r.getDueDate()),
                                    String.format(
                                            "₹%.2f",
                                            r.rentalAmount
                                    ),
                                    String.format(
                                            "₹%.2f",
                                            r.deposit
                                    ),
                                    status
                            }
                    );
                }
            }
        };

        refresh.addActionListener(
                e -> load.run()
        );

        returnButton.addActionListener(
                e -> {

                    int row =
                            table.getSelectedRow();

                    if (row == -1) {

                        showMessage(
                                "Select an active rental."
                        );

                        return;
                    }

                    int id =
                            Integer.parseInt(
                                    table.getValueAt(
                                            row,
                                            0
                                    ).toString()
                            );

                    Rental rental =
                            findRental(id);

                    if (rental != null) {

                        boolean completed =
                                processReturn(
                                        rental
                                );

                        if (completed) {

                            load.run();

                            refreshDashboard();
                        }
                    }
                }
        );

        load.run();

        return panel;
    }

    // ================================================================
    // RETURN PROCESS
    // ================================================================

    /**
     * Opens a full return-settlement dialog for the given active
     * rental: rental/vehicle/customer details, an auto-calculated
     * late-days figure the admin can override, vehicle condition on
     * return, any damage charge, and a live breakdown of how the
     * security deposit settles against what's owed. Returns true if
     * the rental was actually settled (Confirm was clicked with
     * valid input), false if the admin cancelled.
     */
    private boolean processReturn(
            Rental rental
    ) {

        Vehicle vehicle =
                findVehicle(
                        rental.vehicleId
                );

        Customer customer =
                findCustomer(
                        rental.customerId
                );

        final boolean[] confirmed = {false};

        JDialog dialog =
                new JDialog(
                        this,
                        "Return Vehicle - Rental #" + rental.id,
                        true
                );

        dialog.setSize(560, 700);
        dialog.setLocationRelativeTo(this);

        JPanel form =
                new JPanel(
                        new GridBagLayout()
                );

        form.setBackground(CARD);

        form.setBorder(
                new EmptyBorder(25, 30, 15, 30)
        );

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7, 8, 7, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        addFormField(form, gbc, row++, "Customer",
                readOnlyLabel(customer != null ? customer.name : "Unknown"));

        addFormField(form, gbc, row++, "Vehicle",
                readOnlyLabel(vehicle != null ? vehicle.brand + " " + vehicle.model : "Unknown"));

        addFormField(form, gbc, row++, "Rental Date",
                readOnlyLabel(formatDate(rental.rentalDate)));

        addFormField(form, gbc, row++, "Due Date",
                readOnlyLabel(formatDate(rental.getDueDate())));

        addFormField(form, gbc, row++, "Rental Days",
                readOnlyLabel(String.valueOf(rental.days)));

        addFormField(form, gbc, row++, "Rental Amount",
                readOnlyLabel(String.format("₹%.2f", rental.rentalAmount)));

        addFormField(form, gbc, row++, "Security Deposit",
                readOnlyLabel(String.format("₹%.2f", rental.deposit)));

        int autoLate = rental.getAutoLateDays();

        JTextField lateDaysField =
                new JTextField(String.valueOf(autoLate));

        styleTextField(lateDaysField);

        addFormField(form, gbc, row++, "Late Days", lateDaysField);

        JComboBox<String> conditionBox =
                new JComboBox<>(
                        new String[]{
                                "Good - No Damage",
                                "Minor Damage",
                                "Major Damage"
                        }
                );

        styleComboBox(conditionBox);

        addFormField(form, gbc, row++, "Vehicle Condition", conditionBox);

        JTextField damageField =
                new JTextField("0");

        styleTextField(damageField);

        addFormField(form, gbc, row++, "Damage Charges (₹)", damageField);

        JButton calculate = createPrimaryButton("Calculate");

        gbc.gridx = 1;
        gbc.gridy = row++;

        form.add(calculate, gbc);

        JTextArea summary = new JTextArea(7, 28);
        summary.setEditable(false);
        summary.setFont(new Font("Monospaced", Font.PLAIN, 13));
        summary.setBackground(new Color(248, 250, 252));
        summary.setForeground(TEXT);
        summary.setBorder(new CompoundBorder(new LineBorder(BORDER), new EmptyBorder(10, 10, 10, 10)));

        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 2;

        form.add(summary, gbc);

        Runnable recalc = () -> {

            try {

                int late = Integer.parseInt(lateDaysField.getText().trim());

                if (late < 0) {
                    throw new NumberFormatException();
                }

                double damage = damageField.getText().trim().isEmpty()
                        ? 0
                        : Double.parseDouble(damageField.getText().trim());

                if (damage < 0) {
                    throw new NumberFormatException();
                }

                double rate = vehicle != null ? vehicle.ratePerDay : 0;
                double lateCharge = late * rate * 1.25;

                double totalDue = rental.rentalAmount + lateCharge + damage;
                double depositApplied = Math.min(rental.deposit, totalDue);
                double depositRefund = rental.deposit - depositApplied;
                double payableNow = totalDue - depositApplied;

                StringBuilder sb = new StringBuilder();
                sb.append(String.format("Rental Amount     : ₹%.2f%n", rental.rentalAmount));
                sb.append(String.format("Late Charge       : ₹%.2f  (%d day%s)%n", lateCharge, late, late == 1 ? "" : "s"));
                sb.append(String.format("Damage Charge     : ₹%.2f%n", damage));
                sb.append("------------------------------\n");
                sb.append(String.format("Total Due         : ₹%.2f%n", totalDue));
                sb.append(String.format("Deposit Applied   : ₹%.2f%n", depositApplied));
                sb.append(String.format("Deposit Refunded  : ₹%.2f%n", depositRefund));
                sb.append(String.format("Payable Now       : ₹%.2f", payableNow));

                summary.setText(sb.toString());

            } catch (NumberFormatException ex) {

                summary.setText("Enter valid numeric late days and damage charge, then click Calculate.");
            }
        };

        calculate.addActionListener(e -> recalc.run());

        recalc.run();

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        actions.setBackground(CARD);

        JButton confirmButton = createSuccessButton("Confirm Return");
        JButton cancelButton = createSecondaryButton("Cancel");

        confirmButton.addActionListener(e -> {

            int late;
            double damage;

            try {

                late = Integer.parseInt(lateDaysField.getText().trim());

                if (late < 0) {
                    throw new NumberFormatException();
                }

            } catch (NumberFormatException ex) {

                showMessage("Enter a valid number of late days.");
                return;
            }

            try {

                damage = damageField.getText().trim().isEmpty()
                        ? 0
                        : Double.parseDouble(damageField.getText().trim());

                if (damage < 0) {
                    throw new NumberFormatException();
                }

            } catch (NumberFormatException ex) {

                showMessage("Enter a valid damage charge (0 if none).");
                return;
            }

            double rate = vehicle != null ? vehicle.ratePerDay : 0;
            double lateCharge = late * rate * 1.25;

            double totalDue = rental.rentalAmount + lateCharge + damage;
            double depositApplied = Math.min(rental.deposit, totalDue);
            double depositRefund = rental.deposit - depositApplied;
            double payableNow = totalDue - depositApplied;

            rental.lateDays = late;
            rental.lateCharge = lateCharge;
            rental.damageCharge = damage;
            rental.condition = conditionBox.getSelectedItem().toString();
            rental.totalAmount = totalDue;
            rental.depositApplied = depositApplied;
            rental.depositRefund = depositRefund;
            rental.amountPayableNow = payableNow;
            rental.returned = true;
            rental.returnDate = new Date();

            if (vehicle != null) {
                vehicle.available = true;
            }

            confirmed[0] = true;

            dialog.dispose();

            showInvoice(rental);
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        actions.add(cancelButton);
        actions.add(confirmButton);

        dialog.setLayout(new BorderLayout());
        dialog.add(new JScrollPane(form), BorderLayout.CENTER);
        dialog.add(actions, BorderLayout.SOUTH);

        dialog.setVisible(true);

        return confirmed[0];
    }

    /**
     * A styled, non-editable JLabel used to show read-only rental
     * details inside a GridBagLayout form built with addFormField.
     */
    private JLabel readOnlyLabel(String text) {

        JLabel label = new JLabel(text);
        label.setForeground(TEXT);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        return label;
    }

    // ================================================================
    // RENTAL HISTORY
    // ================================================================

    private JPanel createHistoryPanel() {

        JPanel panel =
                createBasePanel();

        JLabel title =
                new JLabel(
                        "Rental History"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        26
                )
        );

        title.setForeground(
                TEXT
        );

        panel.add(
                title,
                BorderLayout.NORTH
        );

        String[] columns = {
                "Rental ID",
                "Customer",
                "Vehicle",
                "Days",
                "Rental",
                "Deposit",
                "Late Charge",
                "Total",
                "Status"
        };

        DefaultTableModel model =
                new DefaultTableModel(
                        columns,
                        0
                );

        JTable table =
                createTable(model);

        JPanel center =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        center.setOpaque(false);

        center.add(
                createTableContainer(
                        table
                ),
                BorderLayout.CENTER
        );

        JPanel buttons =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        buttons.setOpaque(false);

        JButton refresh =
                createSecondaryButton(
                        "Refresh"
                );

        JButton invoice =
                createSuccessButton(
                        "View Invoice"
                );

        buttons.add(refresh);
        buttons.add(invoice);

        center.add(
                buttons,
                BorderLayout.SOUTH
        );

        panel.add(
                center,
                BorderLayout.CENTER
        );

        Runnable load = () -> {

            model.setRowCount(0);

            for (Rental r :
                    rentals) {

                Customer c =
                        findCustomer(
                                r.customerId
                        );

                Vehicle v =
                        findVehicle(
                                r.vehicleId
                        );

                model.addRow(
                        new Object[]{
                                r.id,
                                c != null
                                        ? c.name
                                        : "Unknown",
                                v != null
                                        ? v.brand
                                        + " "
                                        + v.model
                                        : "Unknown",
                                r.days,
                                String.format(
                                        "₹%.2f",
                                        r.rentalAmount
                                ),
                                String.format(
                                        "₹%.2f",
                                        r.deposit
                                ),
                                String.format(
                                        "₹%.2f",
                                        r.lateCharge
                                ),
                                r.returned
                                        ? String.format(
                                        "₹%.2f",
                                        r.totalAmount
                                )
                                        : "-",
                                r.returned
                                        ? "RETURNED"
                                        : "ACTIVE"
                        }
                );
            }
        };

        refresh.addActionListener(
                e -> load.run()
        );

        invoice.addActionListener(
                e -> {

                    int row =
                            table.getSelectedRow();

                    if (row == -1) {

                        showMessage(
                                "Select a rental."
                        );

                        return;
                    }

                    int id =
                            Integer.parseInt(
                                    table.getValueAt(
                                            row,
                                            0
                                    ).toString()
                            );

                    Rental r =
                            findRental(id);

                    if (r != null) {

                        showInvoice(r);
                    }
                }
        );

        load.run();

        return panel;
    }

    // ================================================================
    // REPORTS
    // ================================================================

    private JPanel createReportsPanel() {

        JPanel panel =
                createBasePanel();

        JLabel title =
                new JLabel(
                        "Reports & Analytics"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        26
                )
        );

        title.setForeground(
                TEXT
        );

        panel.add(
                title,
                BorderLayout.NORTH
        );

        JPanel cards =
                new JPanel(
                        new GridLayout(
                                3,
                                3,
                                18,
                                18
                        )
                );

        cards.setOpaque(false);

        int available =
                0;

        int rented =
                0;

        for (Vehicle v :
                vehicles) {

            if (v.available) {

                available++;

            } else {

                rented++;
            }
        }

        cards.add(
                createReportCard(
                        "Total Vehicles",
                        String.valueOf(
                                vehicles.size()
                        ),
                        PRIMARY
                )
        );

        cards.add(
                createReportCard(
                        "Available Vehicles",
                        String.valueOf(
                                available
                        ),
                        SUCCESS
                )
        );

        cards.add(
                createReportCard(
                        "Currently Rented",
                        String.valueOf(
                                rented
                        ),
                        WARNING
                )
        );

        cards.add(
                createReportCard(
                        "Total Customers",
                        String.valueOf(
                                customers.size()
                        ),
                        PRIMARY
                )
        );

        cards.add(
                createReportCard(
                        "Total Revenue (Earned)",
                        String.format(
                                "₹%.2f",
                                getTotalRevenue()
                        ),
                        SUCCESS
                )
        );

        cards.add(
                createReportCard(
                        "This Month's Revenue",
                        String.format(
                                "₹%.2f",
                                getThisMonthRevenue()
                        ),
                        SUCCESS
                )
        );

        cards.add(
                createReportCard(
                        "Today's Revenue",
                        String.format(
                                "₹%.2f",
                                getTodayRevenue()
                        ),
                        PRIMARY
                )
        );

        cards.add(
                createReportCard(
                        "Pending Revenue (Active)",
                        String.format(
                                "₹%.2f",
                                getPendingRevenue()
                        ),
                        WARNING
                )
        );

        cards.add(
                createReportCard(
                        "Deposits Held (Active)",
                        String.format(
                                "₹%.2f",
                                getDepositsHeld()
                        ),
                        DANGER
                )
        );

        panel.add(
                cards,
                BorderLayout.CENTER
        );

        JLabel note = new JLabel(
                "Note: Security deposits are refundable customer funds and are excluded from revenue figures."
        );

        note.setForeground(TEXT_SECONDARY);
        note.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        note.setBorder(new EmptyBorder(15, 5, 0, 0));

        panel.add(note, BorderLayout.SOUTH);

        return panel;
    }

    // ================================================================
    // REPORT CARD
    // ================================================================

    private JPanel createReportCard(
            String title,
            String value,
            Color color
    ) {

        JPanel card =
                createWhiteCard();

        card.setLayout(
                new BorderLayout(
                        10,
                        10
                )
        );

        JLabel t =
                new JLabel(
                        title
                );

        t.setForeground(
                TEXT_SECONDARY
        );

        t.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        JLabel v =
                new JLabel(
                        value
                );

        v.setForeground(
                color
        );

        v.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        30
                )
        );

        card.add(
                t,
                BorderLayout.NORTH
        );

        card.add(
                v,
                BorderLayout.CENTER
        );

        return card;
    }

    // ================================================================
    // INVOICE
    // ================================================================

    private void showInvoice(
            Rental rental
    ) {

        Customer c =
                findCustomer(
                        rental.customerId
                );

        Vehicle v =
                findVehicle(
                        rental.vehicleId
                );

        JTextArea area =
                new JTextArea();

        area.setEditable(false);

        area.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        13
                )
        );

        area.setBackground(
                new Color(
                        248,
                        250,
                        252
                )
        );

        area.setForeground(
                TEXT
        );

        StringBuilder sb =
                new StringBuilder();

        sb.append(
                "================================================\n"
        );

        sb.append(
                "              VEHICLE RENTAL INVOICE\n"
        );

        sb.append(
                "================================================\n\n"
        );

        sb.append(
                "Rental ID       : "
        );

        sb.append(
                rental.id
        );

        sb.append("\n");

        sb.append(
                "Rental Date     : "
        );

        sb.append(
                formatDate(
                        rental.rentalDate
                )
        );

        sb.append("\n");

        if (
                rental.returned
        ) {

            sb.append(
                    "Return Date     : "
            );

            sb.append(
                    formatDate(
                            rental.returnDate
                    )
            );

            sb.append("\n");
        }

        sb.append(
                "\nCUSTOMER DETAILS\n"
        );

        sb.append(
                "------------------------------------------------\n"
        );

        sb.append(
                "Name            : "
        );

        sb.append(
                c != null
                        ? c.name
                        : "Unknown"
        );

        sb.append("\n");

        sb.append(
                "Phone           : "
        );

        sb.append(
                c != null
                        ? c.phone
                        : "Unknown"
        );

        sb.append("\n");

        sb.append(
                "Driving License : "
        );

        sb.append(
                c != null
                        ? c.license
                        : "Unknown"
        );

        sb.append("\n");

        sb.append(
                "\nVEHICLE DETAILS\n"
        );

        sb.append(
                "------------------------------------------------\n"
        );

        sb.append(
                "Vehicle         : "
        );

        sb.append(
                v != null
                        ? v.brand
                        + " "
                        + v.model
                        : "Unknown"
        );

        sb.append("\n");

        sb.append(
                "Registration    : "
        );

        sb.append(
                v != null
                        ? v.registration
                        : "Unknown"
        );

        sb.append("\n");

        sb.append(
                "Category        : "
        );

        sb.append(
                v != null
                        ? v.category
                        : "Unknown"
        );

        sb.append("\n");

        sb.append(
                "\nPAYMENT DETAILS\n"
        );

        sb.append(
                "------------------------------------------------\n"
        );

        sb.append(
                "Rental Days     : "
        );

        sb.append(
                rental.days
        );

        sb.append("\n");

        sb.append(
                "Rental Amount   : ₹"
        );

        sb.append(
                String.format(
                        "%.2f",
                        rental.rentalAmount
                )
        );

        sb.append("\n");

        sb.append(
                "Security Deposit: ₹"
        );

        sb.append(
                String.format(
                        "%.2f",
                        rental.deposit
                )
        );

        sb.append("\n");

        sb.append(
                "Late Days       : "
        );

        sb.append(
                rental.lateDays
        );

        sb.append("\n");

        sb.append(
                "Late Charges    : ₹"
        );

        sb.append(
                String.format(
                        "%.2f",
                        rental.lateCharge
                )
        );

        sb.append("\n");

        if (
                rental.returned
        ) {

            sb.append(
                    "Vehicle Condition: "
            );

            sb.append(
                    rental.condition
            );

            sb.append("\n");

            sb.append(
                    "Damage Charges  : ₹"
            );

            sb.append(
                    String.format(
                            "%.2f",
                            rental.damageCharge
                    )
            );

            sb.append("\n");

            sb.append(
                    "------------------------------------------------\n"
            );

            sb.append(
                    "TOTAL DUE       : ₹"
            );

            sb.append(
                    String.format(
                            "%.2f",
                            rental.totalAmount
                    )
            );

            sb.append(
                    "   (rental + late charge + damage)\n"
            );

            sb.append(
                    "Deposit Applied : ₹"
            );

            sb.append(
                    String.format(
                            "%.2f",
                            rental.depositApplied
                    )
            );

            sb.append(
                    "   (from ₹"
            );

            sb.append(
                    String.format(
                            "%.2f",
                            rental.deposit
                    )
            );

            sb.append(
                    " security deposit)\n"
            );

            sb.append(
                    "Deposit Refunded: ₹"
            );

            sb.append(
                    String.format(
                            "%.2f",
                            rental.depositRefund
                    )
            );

            sb.append(
                    "   (returned to customer)\n"
            );

            sb.append(
                    "------------------------------------------------\n"
            );

            sb.append(
                    "PAYABLE NOW     : ₹"
            );

            sb.append(
                    String.format(
                            "%.2f",
                            rental.amountPayableNow
                    )
            );

            sb.append("\n");
        }

        sb.append(
                "\n================================================\n"
        );

        sb.append(
                "       Thank you for choosing VRMS.\n"
        );

        sb.append(
                "================================================\n"
        );

        area.setText(
                sb.toString()
        );

        area.setCaretPosition(0);

        JScrollPane scroll =
                new JScrollPane(
                        area
                );

        scroll.setPreferredSize(
                new Dimension(
                        580,
                        480
                )
        );

        JDialog dialog =
                new JDialog(
                        this,
                        "Rental Invoice",
                        true
                );

        dialog.setSize(620, 620);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout(10, 10));

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBorder(new EmptyBorder(15, 15, 15, 15));
        wrapper.setBackground(CARD);
        wrapper.add(scroll, BorderLayout.CENTER);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        buttons.setBackground(CARD);

        JButton emailButton = createPrimaryButton("Email to Customer");
        JButton saveButton = createSecondaryButton("Save as File");
        JButton printButton = createSecondaryButton("Print");
        JButton closeButton = createDangerButton("Close");

        emailButton.addActionListener(ev -> emailInvoiceToCustomer(c, rental, sb.toString()));
        saveButton.addActionListener(ev -> saveInvoiceToFile(rental, sb.toString()));
        printButton.addActionListener(ev -> printInvoice(area));
        closeButton.addActionListener(ev -> dialog.dispose());

        buttons.add(emailButton);
        buttons.add(saveButton);
        buttons.add(printButton);
        buttons.add(closeButton);

        wrapper.add(buttons, BorderLayout.SOUTH);

        dialog.add(wrapper, BorderLayout.CENTER);
        dialog.setVisible(true);
    }

    // ================================================================
    // INVOICE DELIVERY - EMAIL
    // ================================================================

    /**
     * Opens the user's default desktop mail client with a "mailto:"
     * link pre-filled with the customer's email, invoice subject and
     * the invoice text as the body. This needs no SMTP server or
     * credentials, which makes it a practical way to "send" an
     * invoice from a database-free desktop Swing app - the
     * administrator just hits Send in whatever mail client opens.
     */
    private void emailInvoiceToCustomer(
            Customer customer,
            Rental rental,
            String invoiceText
    ) {

        if (customer == null || customer.email == null || customer.email.trim().isEmpty()) {

            showMessage(
                    "This customer has no email address on file."
            );

            return;
        }

        if (!Desktop.isDesktopSupported()
                || !Desktop.getDesktop().isSupported(Desktop.Action.MAIL)) {

            showMessage(
                    "No default email client is configured on this machine.\n"
                            + "Use 'Save as File' instead and attach it manually."
            );

            return;
        }

        try {

            String subject = "VRMS Rental Invoice #" + rental.id;

            String mailto = String.format(
                    "mailto:%s?subject=%s&body=%s",
                    customer.email.trim(),
                    URLEncoder.encode(subject, StandardCharsets.UTF_8.toString()).replace("+", "%20"),
                    URLEncoder.encode(invoiceText, StandardCharsets.UTF_8.toString()).replace("+", "%20")
            );

            Desktop.getDesktop().mail(URI.create(mailto));

        } catch (Exception ex) {

            showMessage(
                    "Could not open the mail client: " + ex.getMessage()
            );
        }
    }

    // ================================================================
    // INVOICE DELIVERY - SAVE TO FILE
    // ================================================================

    private void saveInvoiceToFile(
            Rental rental,
            String invoiceText
    ) {

        JFileChooser chooser = new JFileChooser();

        chooser.setDialogTitle("Save Invoice");

        chooser.setFileFilter(
                new FileNameExtensionFilter("Text File (*.txt)", "txt")
        );

        chooser.setSelectedFile(
                new File("Invoice_" + rental.id + ".txt")
        );

        int result = chooser.showSaveDialog(this);

        if (result != JFileChooser.APPROVE_OPTION) {

            return;
        }

        File file = chooser.getSelectedFile();

        if (!file.getName().toLowerCase().endsWith(".txt")) {

            file = new File(file.getParentFile(), file.getName() + ".txt");
        }

        try (FileWriter writer = new FileWriter(file)) {

            writer.write(invoiceText);

            showMessage(
                    "Invoice saved to:\n" + file.getAbsolutePath()
            );

        } catch (IOException ex) {

            showMessage(
                    "Could not save the invoice: " + ex.getMessage()
            );
        }
    }

    // ================================================================
    // INVOICE DELIVERY - PRINT
    // ================================================================

    private void printInvoice(JTextArea area) {

        try {

            boolean printed = area.print();

            if (!printed) {

                showMessage("Printing was cancelled.");
            }

        } catch (PrinterException ex) {

            showMessage("Printing failed: " + ex.getMessage());
        }
    }

    // ================================================================
    // GUI COMPONENT HELPERS
    // ================================================================

    private JPanel createWhiteCard() {

        JPanel panel =
                new JPanel();

        panel.setBackground(
                CARD
        );

        panel.setBorder(
                new CompoundBorder(
                        new LineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                20,
                                20,
                                20,
                                20
                        )
                )
        );

        return panel;
    }

    private JLabel createSectionTitle(
            String text
    ) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        19
                )
        );

        label.setForeground(
                TEXT
        );

        return label;
    }

    // ================================================================
    // BUTTONS
    // ================================================================

    private JButton createPrimaryButton(
            String text
    ) {

        return createButton(
                text,
                PRIMARY,
                Color.WHITE
        );
    }

    private JButton createSuccessButton(
            String text
    ) {

        return createButton(
                text,
                SUCCESS_DARK,
                Color.WHITE
        );
    }

    private JButton createDangerButton(
            String text
    ) {

        return createButton(
                text,
                DANGER,
                Color.WHITE
        );
    }

    private JButton createSecondaryButton(
            String text
    ) {

        return createButton(
                text,
                new Color(
                        51,
                        65,
                        85
                ),
                Color.WHITE
        );
    }

    private JButton createButton(
            String text,
            Color background,
            Color foreground
    ) {

        JButton button =
                new JButton(text);

        button.setBackground(
                background
        );

        button.setForeground(
                foreground
        );

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        button.setOpaque(true);

        button.setContentAreaFilled(
                true
        );

        button.setBorderPainted(
                false
        );

        button.setFocusPainted(
                false
        );

        button.setBorder(
                new EmptyBorder(
                        10,
                        18,
                        10,
                        18
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.addMouseListener(
                new MouseAdapter() {

                    public void mouseEntered(
                            MouseEvent e
                    ) {

                        button.setBackground(
                                background.darker()
                        );

                        button.setForeground(
                                Color.WHITE
                        );
                    }

                    public void mouseExited(
                            MouseEvent e
                    ) {

                        button.setBackground(
                                background
                        );

                        button.setForeground(
                                foreground
                        );
                    }
                }
        );

        return button;
    }

    // ================================================================
    // TEXT FIELD
    // ================================================================

    private void styleTextField(
            JTextField field
    ) {

        field.setPreferredSize(
                new Dimension(
                        230,
                        36
                )
        );

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        field.setForeground(
                TEXT
        );

        field.setBackground(
                Color.WHITE
        );

        field.setCaretColor(
                PRIMARY
        );

        field.setBorder(
                new CompoundBorder(
                        new LineBorder(
                                new Color(
                                        203,
                                        213,
                                        225
                                )
                        ),
                        new EmptyBorder(
                                6,
                                10,
                                6,
                                10
                        )
                )
        );
    }

    // ================================================================
    // COMBO BOX
    // ================================================================

    private void styleComboBox(
            JComboBox<String> box
    ) {

        box.setPreferredSize(
                new Dimension(
                        230,
                        36
                )
        );

        box.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        box.setForeground(
                TEXT
        );

        box.setBackground(
                Color.WHITE
        );

        box.setBorder(
                new LineBorder(
                        new Color(
                                203,
                                213,
                                225
                        )
                )
        );
    }

    // ================================================================
    // TABLE
    // ================================================================

    private JTable createTable(
            DefaultTableModel model
    ) {

        JTable table =
                new JTable(model);

        table.setRowHeight(36);

        table.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        table.setForeground(
                TEXT
        );

        table.setBackground(
                Color.WHITE
        );

        table.setGridColor(
                BORDER
        );

        table.setSelectionBackground(
                new Color(
                        219,
                        234,
                        254
                )
        );

        table.setSelectionForeground(
                TEXT
        );

        table.setShowVerticalLines(
                false
        );

        table.setShowHorizontalLines(
                true
        );

        table.setAutoCreateRowSorter(
                true
        );

        JTableHeader header =
                table.getTableHeader();

        header.setPreferredSize(
                new Dimension(
                        0,
                        42
                )
        );

        header.setBackground(
                new Color(
                        30,
                        41,
                        59
                )
        );

        header.setForeground(
                Color.WHITE
        );

        header.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        header.setReorderingAllowed(
                false
        );

        return table;
    }

    // ================================================================
    // TABLE CONTAINER
    // ================================================================

    private JPanel createTableContainer(
            JTable table
    ) {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(
                Color.WHITE
        );

        panel.setBorder(
                new LineBorder(
                        BORDER
                )
        );

        panel.add(
                new JScrollPane(table),
                BorderLayout.CENTER
        );

        return panel;
    }

    // ================================================================
    // FORM FIELD
    // ================================================================

    private void addFormField(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            String label,
            JComponent component
    ) {

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;

        JLabel l =
                new JLabel(label);

        l.setForeground(
                TEXT
        );

        l.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        panel.add(
                l,
                gbc
        );

        gbc.gridx = 1;
        gbc.weightx = 1;

        panel.add(
                component,
                gbc
        );
    }

    // ================================================================
    // COMBO DATA
    // ================================================================

    private void loadCustomerCombo(
            JComboBox<String> box
    ) {

        box.removeAllItems();

        for (Customer c :
                customers) {

            box.addItem(
                    c.id
                            + " - "
                            + c.name
            );
        }
    }

    private void loadVehicleCombo(
            JComboBox<String> box
    ) {

        box.removeAllItems();

        for (Vehicle v :
                vehicles) {

            if (v.available) {

                box.addItem(
                        v.id
                                + " - "
                                + v.registration
                                + " - "
                                + v.brand
                                + " "
                                + v.model
                );
            }
        }
    }

    // ================================================================
    // FIND METHODS
    // ================================================================

    private Vehicle findVehicle(
            int id
    ) {

        for (Vehicle v :
                vehicles) {

            if (v.id == id) {

                return v;
            }
        }

        return null;
    }

    private Customer findCustomer(
            int id
    ) {

        for (Customer c :
                customers) {

            if (c.id == id) {

                return c;
            }
        }

        return null;
    }

    private Rental findRental(
            int id
    ) {

        for (Rental r :
                rentals) {

            if (r.id == id) {

                return r;
            }
        }

        return null;
    }

    private Vehicle findVehicleFromCombo(
            String text
    ) {

        try {

            int id =
                    Integer.parseInt(
                            text
                                    .split("-")[0]
                                    .trim()
                    );

            return findVehicle(id);

        } catch (
                Exception e
        ) {

            return null;
        }
    }

    private Customer findCustomerFromCombo(
            String text
    ) {

        try {

            int id =
                    Integer.parseInt(
                            text
                                    .split("-")[0]
                                    .trim()
                    );

            return findCustomer(id);

        } catch (
                Exception e
        ) {

            return null;
        }
    }

    // ================================================================
    // DASHBOARD REFRESH
    // ================================================================

    private void refreshDashboard() {

        if (
                dashboardVehicles == null
        ) {

            return;
        }

        dashboardVehicles.setText(
                String.valueOf(
                        vehicles.size()
                )
        );

        dashboardCustomers.setText(
                String.valueOf(
                        customers.size()
                )
        );

        int active =
                0;

        double revenue =
                0;

        for (Rental r :
                rentals) {

            if (!r.returned) {

                active++;

            } else {

                revenue +=
                        r.getRevenue();
            }
        }

        dashboardRentals.setText(
                String.valueOf(
                        active
                )
        );

        dashboardRevenue.setText(
                String.format(
                        "₹%.2f",
                        revenue
                )
        );
    }

    // ================================================================
    // REVENUE CALCULATIONS
    // ================================================================

    /**
     * Sum of rentalAmount + lateCharge for every RETURNED rental.
     * This is the only figure that should ever be called "revenue" -
     * deposits are excluded because they are refundable customer
     * money, not company earnings.
     */
    private double getTotalRevenue() {

        double total = 0;

        for (Rental r : rentals) {

            if (r.returned) {

                total += r.getRevenue();
            }
        }

        return total;
    }

    /**
     * Revenue earned from rentals returned today (calendar day match
     * on returnDate).
     */
    private double getTodayRevenue() {

        double total = 0;

        Calendar today = Calendar.getInstance();

        for (Rental r : rentals) {

            if (r.returned && r.returnDate != null) {

                Calendar rd = Calendar.getInstance();
                rd.setTime(r.returnDate);

                if (
                        rd.get(Calendar.YEAR) == today.get(Calendar.YEAR)
                                &&
                                rd.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)
                ) {

                    total += r.getRevenue();
                }
            }
        }

        return total;
    }

    /**
     * Revenue earned from rentals returned in the current calendar
     * month.
     */
    private double getThisMonthRevenue() {

        double total = 0;

        Calendar now = Calendar.getInstance();

        for (Rental r : rentals) {

            if (r.returned && r.returnDate != null) {

                Calendar rd = Calendar.getInstance();
                rd.setTime(r.returnDate);

                if (
                        rd.get(Calendar.YEAR) == now.get(Calendar.YEAR)
                                &&
                                rd.get(Calendar.MONTH) == now.get(Calendar.MONTH)
                ) {

                    total += r.getRevenue();
                }
            }
        }

        return total;
    }

    /**
     * Projected revenue still to be collected once currently active
     * rentals are returned (base rental amount only - late charges
     * are unknown until return).
     */
    private double getPendingRevenue() {

        double total = 0;

        for (Rental r : rentals) {

            if (!r.returned) {

                total += r.rentalAmount;
            }
        }

        return total;
    }

    /**
     * Total security deposit currently held for active (not yet
     * returned) rentals - this is customer money, not revenue, but
     * useful for the business to track as a liability.
     */
    private double getDepositsHeld() {

        double total = 0;

        for (Rental r : rentals) {

            if (!r.returned) {

                total += r.deposit;
            }
        }

        return total;
    }

    // ================================================================
    // LOGOUT
    // ================================================================

    private void logout() {

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Do you want to logout?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                result ==
                        JOptionPane.YES_OPTION
        ) {

            dispose();

            new LoginFrame();
        }
    }

    // ================================================================
    // MESSAGE
    // ================================================================

    private void showMessage(
            String message
    ) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Vehicle Rental System",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // ================================================================
    // DATE
    // ================================================================

    private String formatDate(
            Date date
    ) {

        if (date == null) {

            return "-";
        }

        return new SimpleDateFormat(
                "dd-MM-yyyy HH:mm"
        ).format(date);
    }

    // ================================================================
    // SAMPLE DATA
    // ================================================================

    private void initializeSampleData() {

        vehicles.add(
                new Vehicle(
                        vehicleCounter++,
                        "MH-04-AB-1234",
                        "Maruti",
                        "Swift",
                        "Hatchback",
                        "Petrol",
                        1500
                )
        );

        vehicles.add(
                new Vehicle(
                        vehicleCounter++,
                        "MH-01-CD-4567",
                        "Hyundai",
                        "Creta",
                        "SUV",
                        "Diesel",
                        2500
                )
        );

        vehicles.add(
                new Vehicle(
                        vehicleCounter++,
                        "MH-02-EF-7890",
                        "Honda",
                        "City",
                        "Sedan",
                        "Petrol",
                        2200
                )
        );

        vehicles.add(
                new Vehicle(
                        vehicleCounter++,
                        "MH-43-GH-1122",
                        "Tata",
                        "Nexon EV",
                        "Electric",
                        "Electric",
                        2800
                )
        );

        vehicles.add(
                new Vehicle(
                        vehicleCounter++,
                        "MH-05-JK-3344",
                        "Royal Enfield",
                        "Classic 350",
                        "Bike",
                        "Petrol",
                        900
                )
        );

        customers.add(
                new Customer(
                        customerCounter++,
                        "Rahul Sharma",
                        "9876543210",
                        "rahul@gmail.com",
                        "MH12 20240012345",
                        "Mumbai"
                )
        );

        customers.add(
                new Customer(
                        customerCounter++,
                        "Amit Patil",
                        "9123456780",
                        "amit@gmail.com",
                        "MH14 20230056789",
                        "Navi Mumbai"
                )
        );
    }

    // ================================================================
    // VEHICLE CLASS
    // ================================================================

    static class Vehicle {

        int id;

        String registration;
        String brand;
        String model;
        String category;
        String fuel;

        double ratePerDay;

        boolean available;

        Vehicle(
                int id,
                String registration,
                String brand,
                String model,
                String category,
                String fuel,
                double ratePerDay
        ) {

            this.id = id;
            this.registration = registration;
            this.brand = brand;
            this.model = model;
            this.category = category;
            this.fuel = fuel;
            this.ratePerDay = ratePerDay;
            this.available = true;
        }
    }

    // ================================================================
    // CUSTOMER CLASS
    // ================================================================

    static class Customer {

        int id;

        String name;
        String phone;
        String email;
        String license;
        String address;

        Customer(
                int id,
                String name,
                String phone,
                String email,
                String license,
                String address
        ) {

            this.id = id;
            this.name = name;
            this.phone = phone;
            this.email = email;
            this.license = license;
            this.address = address;
        }
    }

    // ================================================================
    // RENTAL CLASS
    // ================================================================

    static class Rental {

        int id;

        int customerId;
        int vehicleId;

        int days;

        double rentalAmount;
        double deposit;

        int lateDays;

        double lateCharge;

        // Condition reported at return time, and any repair/cleaning
        // charge billed for it.
        String condition;
        double damageCharge;

        // How much of the deposit was used to cover late/damage
        // charges, how much was refunded, and any extra the customer
        // still owes if charges exceeded the deposit.
        double depositApplied;
        double depositRefund;
        double amountPayableNow;

        // Total amount actually EARNED by the business on this
        // rental (rentalAmount + lateCharge + damageCharge). The
        // deposit is deliberately excluded - it is the customer's
        // own refundable money, never revenue.
        double totalAmount;

        Date rentalDate;
        Date returnDate;

        boolean returned;

        Rental(
                int id,
                int customerId,
                int vehicleId,
                int days,
                double rentalAmount,
                double deposit
        ) {

            this.id = id;
            this.customerId = customerId;
            this.vehicleId = vehicleId;
            this.days = days;

            this.rentalAmount =
                    rentalAmount;

            this.deposit =
                    deposit;

            this.lateDays = 0;

            this.lateCharge = 0;

            this.condition = "Not yet returned";
            this.damageCharge = 0;

            this.depositApplied = 0;
            this.depositRefund = 0;
            this.amountPayableNow = 0;

            this.totalAmount = 0;

            this.rentalDate =
                    new Date();

            this.returned = false;
        }

        /**
         * The expected return date: rentalDate + the number of days
         * booked. Used to auto-calculate late days at return time.
         */
        Date getDueDate() {

            Calendar cal = Calendar.getInstance();
            cal.setTime(this.rentalDate);
            cal.add(Calendar.DAY_OF_MONTH, this.days);

            return cal.getTime();
        }

        /**
         * Whole days between now and the due date, 0 if not yet
         * overdue. Used to pre-fill the late-days field at return
         * time so the admin doesn't have to calculate it by hand.
         */
        int getAutoLateDays() {

            long diffMillis =
                    new Date().getTime() - getDueDate().getTime();

            if (diffMillis <= 0) {

                return 0;
            }

            return (int) Math.ceil(diffMillis / (1000.0 * 60 * 60 * 24));
        }

        /**
         * Actual money earned by the business on this rental.
         * The security deposit is NOT revenue - it is the customer's
         * own money being held and refunded back to them, so it must
         * never be added into revenue totals.
         */
        double getRevenue() {

            return this.totalAmount;
        }
    }

    // ================================================================
    // LOGIN FRAME
    // ================================================================

    static class LoginFrame
            extends JFrame {

        JTextField username;

        JPasswordField password;

        LoginFrame() {

            setTitle(
                    "VRMS | Administrator Login"
            );

            setSize(
                    520,
                    440
            );

            setLocationRelativeTo(
                    null
            );

            setDefaultCloseOperation(
                    JFrame.EXIT_ON_CLOSE
            );

            createLoginUI();

            setVisible(true);
        }

        private void createLoginUI() {

            JPanel background =
                    new JPanel(
                            new GridBagLayout()
                    );

            background.setBackground(
                    DARK_BG
            );

            JPanel card =
                    new JPanel(
                            new GridBagLayout()
                    );

            card.setPreferredSize(
                    new Dimension(
                            390,
                            330
                    )
            );

            card.setBackground(
                    Color.WHITE
            );

            card.setBorder(
                    new EmptyBorder(
                            30,
                            35,
                            30,
                            35
                    )
            );

            GridBagConstraints gbc =
                    new GridBagConstraints();

            gbc.insets =
                    new Insets(
                            8,
                            8,
                            8,
                            8
                    );

            gbc.fill =
                    GridBagConstraints.HORIZONTAL;

            JLabel logo =
                    new JLabel(
                            "VRMS",
                            SwingConstants.CENTER
                    );

            logo.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            35
                    )
            );

            logo.setForeground(
                    PRIMARY
            );

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;

            card.add(
                    logo,
                    gbc
            );

            JLabel title =
                    new JLabel(
                            "Vehicle Rental Management",
                            SwingConstants.CENTER
                    );

            title.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            17
                    )
            );

            title.setForeground(
                    TEXT
            );

            gbc.gridy = 1;

            card.add(
                    title,
                    gbc
            );

            JLabel subtitle =
                    new JLabel(
                            "Administrator Login",
                            SwingConstants.CENTER
                    );

            subtitle.setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            12
                    )
            );

            subtitle.setForeground(
                    TEXT_SECONDARY
            );

            gbc.gridy = 2;

            card.add(
                    subtitle,
                    gbc
            );

            gbc.gridwidth = 1;

            username =
                    new JTextField();

            password =
                    new JPasswordField();

            styleLoginField(
                    username
            );

            styleLoginField(
                    password
            );

            gbc.gridy = 3;
            gbc.gridx = 0;

            card.add(
                    new JLabel(
                            "Username"
                    ),
                    gbc
            );

            gbc.gridx = 1;

            card.add(
                    username,
                    gbc
            );

            gbc.gridy = 4;
            gbc.gridx = 0;

            card.add(
                    new JLabel(
                            "Password"
                    ),
                    gbc
            );

            gbc.gridx = 1;

            card.add(
                    password,
                    gbc
            );

            JButton login =
                    new JButton(
                            "LOGIN"
                    );

            login.setBackground(
                    PRIMARY
            );

            login.setForeground(
                    Color.WHITE
            );

            login.setFont(
                    new Font(
                            "Segoe UI",
                            Font.BOLD,
                            14
                    )
            );

            login.setOpaque(true);

            login.setContentAreaFilled(
                    true
            );

            login.setBorderPainted(
                    false
            );

            login.setFocusPainted(
                    false
            );

            login.setBorder(
                    new EmptyBorder(
                            11,
                            20,
                            11,
                            20
                    )
            );

            gbc.gridy = 6;
            gbc.gridx = 0;
            gbc.gridwidth = 2;

            card.add(
                    login,
                    gbc
            );

            JLabel demo =
                    new JLabel(
                            "Demo Login: admin / admin123",
                            SwingConstants.CENTER
                    );

            demo.setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            11
                    )
            );

            demo.setForeground(
                    TEXT_LIGHT
            );

            gbc.gridy = 7;

            card.add(
                    demo,
                    gbc
            );

            login.addActionListener(
                    e -> login()
            );

            password.addActionListener(
                    e -> login()
            );

            background.add(
                    card
            );

            add(
                    background
            );
        }

        private void styleLoginField(
                JTextField field
        ) {

            field.setPreferredSize(
                    new Dimension(
                            190,
                            36
                    )
            );

            field.setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            13
                    )
            );

            field.setForeground(
                    TEXT
            );

            field.setBackground(
                    Color.WHITE
            );

            field.setBorder(
                    new CompoundBorder(
                            new LineBorder(
                                    new Color(
                                            203,
                                            213,
                                            225
                                    )
                            ),
                            new EmptyBorder(
                                    6,
                                    8,
                                    6,
                                    8
                            )
                    )
            );
        }

        private void login() {

            String user =
                    username
                            .getText()
                            .trim();

            String pass =
                    new String(
                            password
                                    .getPassword()
                    );

            if (
                    user.equals(
                            "admin"
                    )
                            &&
                            pass.equals(
                                    "admin123"
                            )
            ) {

                dispose();

                SwingUtilities.invokeLater(
                        () -> {

                            VehicleRentalSystem app =
                                    new VehicleRentalSystem();

                            app.setVisible(
                                    true
                            );
                        }
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid username or password.",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    // ================================================================
    // MAIN METHOD
    // ================================================================

    public static void main(
            String[] args
    ) {

        try {

            UIManager.setLookAndFeel(
                    UIManager
                            .getSystemLookAndFeelClassName()
            );

        } catch (
                Exception ignored
        ) {
        }

        SwingUtilities.invokeLater(
                () -> new LoginFrame()
        );
    }
}