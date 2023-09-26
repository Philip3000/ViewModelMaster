package com.example.viewmodelmaster

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.viewmodelmaster.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PersonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonGreet.setOnClickListener {
            val name = binding.editTextName.text.trim().toString()
            if (name.isEmpty()) {
                binding.editTextName.error = "No name"
                return@setOnClickListener
            }
            val ageStr = binding.editTextAge.text.trim().toString()
            if (ageStr.isEmpty()) {
                binding.editTextAge.error = "No age"
                return@setOnClickListener
            }

            val ageInt = ageStr.toInt()
            viewModel.add(Person(name, ageInt))


        }

        viewModel.persons.observe(viewLifecycleOwner) { persons ->
            // Clear any previous UI content
            binding.textviewPerson.text = ""

            // Iterate through the 'persons' list and display them
            persons.forEach { person ->
                val personInfo = "Hello ${person.name}. You modelview are ${person.age} years old"

                binding.textviewPerson.append("$personInfo\n")
            }
        }





        binding.buttonNext.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}