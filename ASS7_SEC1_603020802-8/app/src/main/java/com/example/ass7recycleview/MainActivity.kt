package com.example.ass7recycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_dialog_layout.view.*
import kotlinx.android.synthetic.main.std_item_layout.view.*
import layout.Employee
import java.security.acl.Group

class MainActivity : AppCompatActivity() {
    val EmployeeList = arrayListOf<Employee>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        testEmployeeData()
        recycler_view.adapter = EmployeeAdapter(this.EmployeeList,applicationContext)
        recycler_view.layoutManager = LinearLayoutManager(applicationContext) as RecyclerView.LayoutManager?
        recycler_view.itemAnimator = DefaultItemAnimator()

    }

    fun addEmployee(v: View) {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.add_dialog_layout, null)
        val mBuilder = AlertDialog.Builder(this)
        mBuilder.setView(mDialogView)
        val mAlertDialog = mBuilder.show()

        mDialogView.btnAdd.setOnClickListener {

            var RadioGroup :Int = mDialogView.radioGroup.checkedRadioButtonId;
            var radioButton :RadioButton = mDialogView.findViewById(RadioGroup);

            EmployeeList.add( Employee( mDialogView.edit_name.text.toString(),
                radioButton.text.toString(),
                mDialogView.edit_email.text.toString(),
                mDialogView.edit_salary.text.toString().toInt())
            )
            recycler_view.adapter?.notifyDataSetChanged()
            Toast.makeText(applicationContext,"The Employee is added successfully", Toast.LENGTH_SHORT).show()
            mAlertDialog.dismiss()
        }

        mDialogView.btnCancel.setOnClickListener() {
            mAlertDialog.dismiss()
        }
    }

    fun testEmployeeData() {
        EmployeeList.add(Employee("Danny", "Male", "Danny@kku.ac.th",30000))
    }
}